package com.lvb.courses.app_phrases_day

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var btnGenerate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGenerate = findViewById(R.id.btnNewPhrase)
        btnGenerate.setOnClickListener {generateNewPhrase(it)}
    }

    fun generateNewPhrase(view: View) {
        var txtPhrase : TextView = findViewById(R.id.txtPhrase)
        var phrases = arrayOf(
            "As long as there is a will to fight, there will be hope to win.",
            "The important thing is not to win every day, but to fight always",
            "Imagine a new story for your life and believe in it",
            "Greater than the sadness of not having won is the shame of not having fought!"
        )

        var index : Int = Random.nextInt(0, phrases.size)
        txtPhrase.text = phrases[index]
    }
}