package com.lvb.courses.app_tupicionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btnAnimals: Button
    lateinit var btnCooking: Button
    lateinit var btnPlants: Button
    lateinit var btnPeople: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAnimals = findViewById(R.id.btnAnimal)
        btnAnimals.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AnimalsActivity::class.java);
            startActivity(intent);
        })

        btnCooking = findViewById(R.id.btnCooking)
        btnCooking.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, CoockingActivity::class.java);
            startActivity(intent);
        })

        btnPlants = findViewById(R.id.btnPlants)
        btnPlants.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PlantsActivity::class.java);
            startActivity(intent);
        })

        btnPeople = findViewById(R.id.btnPeople)
        btnPeople.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PeopleActivity::class.java);
            startActivity(intent);
        })


    }
}