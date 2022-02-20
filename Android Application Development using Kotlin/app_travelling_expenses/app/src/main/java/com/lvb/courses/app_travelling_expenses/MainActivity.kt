package com.lvb.courses.app_travelling_expenses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalculate : Button
    private lateinit var txtEditDistance : EditText
    private lateinit var txtEditPrice : EditText
    private lateinit var txtEditAutonomy : EditText
    private lateinit var txtResultExpanse: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtEditDistance = findViewById(R.id.txtEditDistance)
        txtEditPrice = findViewById(R.id.txtEditPrice)
        txtEditAutonomy = findViewById(R.id.txtEditAutonomy)
        txtResultExpanse = findViewById(R.id.txtResultExpanse)

        btnCalculate = findViewById(R.id.btnCalculate)
        btnCalculate.setOnClickListener{ calculateValue(it) }
    }

    private fun calculateValue(view : View) {

        if(btnCalculate.id == view.id && validateFields()) {

            try {
                val distance = txtEditDistance.text.toString().toFloat()
                val price = txtEditPrice.text.toString().toFloat()
                val autonomy = txtEditAutonomy.text.toString().toFloat()

                val totalValue = (distance * price) / autonomy
                txtResultExpanse.text = "${"%.2f".format(totalValue)}";
            }
            catch(e : NumberFormatException) {
                Toast.makeText(this, "Inform valid values", Toast.LENGTH_LONG).show()
            }
        }

        else {
            Toast.makeText(this, "Fill all Fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateFields(): Boolean {

        return (txtEditDistance.text.toString().isNotEmpty() &&
                txtEditPrice.text.toString().isNotEmpty() &&
                txtEditAutonomy.text.toString().isNotEmpty())
    }
}