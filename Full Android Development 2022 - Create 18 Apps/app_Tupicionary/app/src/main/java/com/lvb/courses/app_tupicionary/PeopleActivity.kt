package com.lvb.courses.app_tupicionary

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class PeopleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        val povosNativosArray = resources.getStringArray(R.array.povos_nativos)
        val povosNativosDesc = resources.getStringArray(R.array.povos_nativos_desc)

        val list = ArrayList<Item>()
        for (i in 0..9) {
            val titulo = povosNativosArray[i]
            val desc = povosNativosDesc[i]
            val item = Item(titulo, desc, R.drawable.ic_povos_nativos)
            list.add(item)
        }

        val adapter = ItemAdapter(this, list, R.color.povos_nativos_categoria)

        val listView = findViewById<ListView>(R.id.rootPovosNativos)
        listView.adapter = adapter
    }
}