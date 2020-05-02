package com.ppc.todoboom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.textView)
        val create = findViewById<Button>(R.id.createButton)
        val inputText = findViewById<EditText>(R.id.inputText)

        create.setOnClickListener {
            if (inputText.text.equals("")) {
                Toast.makeText(applicationContext, R.string.empty_error, Toast.LENGTH_SHORT).show()
            } else {
                tv.text = inputText.text.toString()
            }
            inputText.setText("")
        }
    }
}
