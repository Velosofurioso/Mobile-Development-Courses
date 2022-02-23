package com.lvb.courses.app_motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lvb.courses.app_motivation.R
import com.lvb.courses.app_motivation.utils.Constants
import com.lvb.courses.app_motivation.utils.SharedPreferences

class SplashActivity : AppCompatActivity() {

    private lateinit var mSharedPrefs: SharedPreferences

    lateinit var btnSave : Button
    lateinit var editName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mSharedPrefs = SharedPreferences(this)
        verifyName();

        editName = findViewById(R.id.editName)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {onClick(it)}

    }

    private fun onClick(view: View) {

        if(view.id == R.id.btnSave) {
            saveName()
        }

    }


    private fun verifyName() {
        val name = mSharedPrefs.getString(Constants.KEY.PERSON_NAME)
        if(name != "") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun saveName() {

        val name = editName.text.toString()

        if(name != "") {
            mSharedPrefs.storeString(Constants.KEY.PERSON_NAME, name)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        else {
            Toast.makeText(applicationContext, "fill in your name!", Toast.LENGTH_LONG).show()
        }

    }
}