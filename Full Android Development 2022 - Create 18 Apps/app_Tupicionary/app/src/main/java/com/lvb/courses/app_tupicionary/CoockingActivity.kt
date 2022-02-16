package com.lvb.courses.app_tupicionary

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class CoockingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coocking)

        val culinariaArray = resources.getStringArray(R.array.culinaria)
        val culinariaDesc = resources.getStringArray(R.array.culinaria_desc)

        val list = ArrayList<Item>()
        for (i in 0..9) {
            val titulo = culinariaArray[i]
            val desc = culinariaDesc[i]
            val item = Item(titulo, desc, R.drawable.ic_culinaria)
            list.add(item)
        }

        val adapter = ItemAdapter(this, list, R.color.culinaria_categoria)

        val listView = findViewById<ListView>(R.id.rootCulinaria)
        listView.adapter = adapter
    }
}