package com.lvb.courses.app_tupicionary

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class PlantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plants)


        val plantasArray = resources.getStringArray(R.array.plantas)
        val plantasDesc = resources.getStringArray(R.array.plantas_desc)

        val list = ArrayList<Item>()
        for (i in 0..9) {
            val titulo = plantasArray[i]
            val desc = plantasDesc[i]
            val item = Item(titulo, desc, R.drawable.ic_plantas)
            list.add(item)
        }

        val adapter = ItemAdapter(this, list, R.color.plantas_categoria)

        val listView = findViewById<ListView>(R.id.rootPlantas)
        listView.adapter = adapter
    }
}