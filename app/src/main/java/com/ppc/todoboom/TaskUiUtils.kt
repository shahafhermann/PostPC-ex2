package com.ppc.todoboom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


data class Task(val description: String, var done: Boolean = false) {
    fun setDone() {
        done = true
    }

    fun isDone(): Boolean {
        return done
    }
}

class TaskHolder(view: View): RecyclerView.ViewHolder(view){
    val text: TextView = view.findViewById(R.id.taskText)

    fun setDone() {
        text.paintFlags = text.paintFlags or STRIKE_THRU_TEXT_FLAG
        text.setTextColor(Color.argb(50, 180, 180, 180))
    }
}

class TaskAdapter(layoutId: Int): RecyclerView.Adapter<TaskHolder>() {

    private var elementLayout = layoutId
    private var _tasks :MutableList<Task> = ArrayList()

    fun getTasks(): MutableList<Task> {
        return _tasks
    }

    fun setTask(tasks : Task){
        _tasks.add(tasks)
        notifyDataSetChanged()
    }

    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {

        val context = parent.context
        val view = LayoutInflater.from(context).inflate(elementLayout, parent, false)
        val holder = TaskHolder(view)

        view.setOnClickListener {
            val task = _tasks[holder.adapterPosition]
            if (!task.isDone()){
                completeTask(task, holder, context)
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return _tasks.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = _tasks[position]
        holder.text.text = task.description
        if (task.isDone()){
            holder.setDone()
        }
    }

    /**
     * Complete a task.
     * @param task The task that's complete
     * @param holder The holder for the task
     */
    private fun completeTask(task: Task, holder: TaskHolder, context: Context) {
        task.setDone()
        holder.setDone()
        val res = context.resources
        val doneMsg = res.getString(R.string.done_msg, task.description)
        val toast = Toast.makeText(context, doneMsg, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}