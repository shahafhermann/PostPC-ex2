package com.ppc.todoboom

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

data class Task(val description: String) {
    var isDone: Boolean = false
}


class TaskHolder(view: View): RecyclerView.ViewHolder(view) {
    val text: TextView = view.findViewById(R.id.taskText)
}

private object TaskDiffCallback: DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.description == newItem.description
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}

class TaskListAdapter: ListAdapter<Task, TaskHolder>(TaskDiffCallback) {
    var onItemClickCallback: ((Task, TaskHolder) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_one_task, parent, false)
        val holder = TaskHolder(view)

        view.setOnClickListener {
            val callback = onItemClickCallback ?: return@setOnClickListener
            val task = getItem(holder.adapterPosition)
            callback(task, holder)
        }

        return holder
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = getItem(position)
        holder.text.text = task.description
    }
}