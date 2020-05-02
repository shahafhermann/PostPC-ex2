package com.ppc.todoboom

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

//    val create = findViewById<Button>(R.id.createButton)
//    val inputText = findViewById<EditText>(R.id.inputText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val tv = findViewById<TextView>(R.id.textView)
//
//        create.setOnClickListener {
//            if (inputText.text.equals("")) {
//                Toast.makeText(applicationContext, R.string.empty_error, Toast.LENGTH_SHORT).show()
//            } else {
//                tv.text = inputText.text.toString()
//                TODO("Add the new task to the recycler")
//            }
//            inputText.setText("")
//        }

        setupListAdapter()
    }

    private fun setupListAdapter(){
        val create = findViewById<Button>(R.id.createButton)
        val inputText = findViewById<EditText>(R.id.inputText)

        val context = this
        val tasks = mutableListOf<Task>()
        val adapter = TaskListAdapter()

        task_recycler.adapter = adapter
        task_recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapter.onItemClickCallback = { task, holder ->
            Toast.makeText(this, "Tap on ${task.description}", Toast.LENGTH_SHORT).show()
            if (!task.isDone) {
                task.isDone = true
                holder.text.setTextColor(Color.argb(40, 180, 180, 180))
                adapter.submitList(tasks.toList())
            }
        }

        create.setOnClickListener {
            if (inputText.text.toString() == "") {
                Toast.makeText(applicationContext, R.string.empty_error, Toast.LENGTH_SHORT).show()
            } else {
                val task = Task(description = inputText.text.toString())

//                if (tasks.isEmpty()) {
//                    tasks += task
//                } else {
//                    val index = Random.nextInt(until = tasks.size)
//                    tasks.add(index, task)
//                }
                tasks.add(task)
                adapter.submitList(tasks)

            }
        }
    }
}
