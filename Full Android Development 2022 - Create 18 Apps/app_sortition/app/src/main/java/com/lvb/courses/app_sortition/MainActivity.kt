package com.lvb.courses.app_sortition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var textResult : TextView
    lateinit var btnGenerate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.textResult)
        btnGenerate = findViewById(R.id.btnGenerate)

        btnGenerate.setOnClickListener{generateNumber(it)}

    }



    private fun generateNumber(view: View) {
        textResult.text = "Generated Number: ${Random.nextInt(0, 10).toString()}";
    }

}