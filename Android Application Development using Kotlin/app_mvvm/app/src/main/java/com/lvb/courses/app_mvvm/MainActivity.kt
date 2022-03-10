package com.lvb.courses.app_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private lateinit var txtTemplate : TextView
    private lateinit var btnLogin : Button
    private lateinit var editText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTemplate = findViewById(R.id.txtTemplate)
        btnLogin = findViewById(R.id.btnLogin)
        editText = findViewById(R.id.editText)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.textWelcome.observe(this) {
            txtTemplate.text = it
        }

        btnLogin.setOnClickListener{
            val name = editText.text.toString()
            viewModel.login(name)
        }

        viewModel.login.observe(this) {
            if(it) {
                Toast.makeText(applicationContext, "Sucess", Toast.LENGTH_SHORT).show()
            }

            else {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}