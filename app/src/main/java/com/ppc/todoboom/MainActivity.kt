package com.ppc.todoboom

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    /* The adapter for the recycler view */
    private val adapter = TaskAdapter(R.layout.item_one_task)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null){
            val taskNumber = savedInstanceState.getInt("taskNumber")
            for (i in 0 until taskNumber){
                val taskDescription = savedInstanceState.getString("description" + i.toString()).toString()
                val taskDone = savedInstanceState.getBoolean("done" + i.toString())
                adapter.addTask(Task(taskDescription,taskDone))
            }
        }

        val create: Button = findViewById(R.id.createButton)
        val inputText: EditText = findViewById(R.id.inputText)

        val recycler:RecyclerView = findViewById(R.id.task_recycler)
        recycler.layoutManager =  LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.adapter = adapter

        create.setOnClickListener {
            if (inputText.text.isEmpty()) {
                val toast = Toast.makeText(applicationContext, R.string.empty_error, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            } else {
                val task = Task(inputText.text.toString())
                inputText.text.clear()

                // Hide the keyboard
                val manager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(it.windowToken, 0)

                adapter.addTask(task)
                adapter.notifyItemInserted(adapter.itemCount - 1)
            }
        }
    }

    /**
     * Handle instance save to enable the app to survive changes
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val tasks = adapter.getTasks()
        var i = 0
        for (task in tasks){
            outState.putString("description" + i.toString(), task.description)
            outState.putBoolean("done" + i.toString(), task.done)
            ++i
        }
        outState.putInt("taskNumber", i)
    }

}