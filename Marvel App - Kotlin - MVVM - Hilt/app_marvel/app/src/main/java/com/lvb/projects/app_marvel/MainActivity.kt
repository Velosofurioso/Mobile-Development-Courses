package com.lvb.projects.app_marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lvb.projects.app_marvel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}