package com.lvb.courses.app_tupicionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class AnimalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animals)

        val bichosArray = resources.getStringArray(R.array.bichos)
        val bichosDescArray = resources.getStringArray(R.array.bichos_desc)

        val list: ArrayList<Item> = ArrayList<Item>()

        for (i in 0..9) {
            val titulo = bichosArray[i]
            val desc = bichosDescArray[i]
            val item = Item(titulo, desc, R.drawable.ic_bichos)
            list.add(item)
        }

        val adapter = ItemAdapter(this, list, R.color.bichos_categoria)

        val listView = findViewById<ListView>(R.id.rootBichos)
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val desc = bichosDescArray[position]
                Toast.makeText(this, desc, Toast.LENGTH_LONG).show()
            }
        listView.adapter = adapter
    }
}