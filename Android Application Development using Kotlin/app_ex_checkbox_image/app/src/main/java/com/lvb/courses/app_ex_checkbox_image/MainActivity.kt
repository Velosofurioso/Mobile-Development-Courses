package com.lvb.courses.app_ex_checkbox_image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var chkColor : CheckBox
    lateinit var icAndroid: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chkColor = findViewById(R.id.chkColor)
        chkColor.setOnClickListener{changeIconColor(it)}

        icAndroid = findViewById(R.id.icAndroid)
    }

    private fun changeIconColor(view : View) {

        if(view.id == chkColor.id && chkColor.isChecked) {
            icAndroid.setColorFilter(R.color.purple_700)
        }

        else if(view.id == chkColor.id && !chkColor.isChecked) {
            icAndroid.setColorFilter(R.color.black)
        }

    }
}