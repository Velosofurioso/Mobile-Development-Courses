package com.lvb.courses.app_heads_or_tails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lvb.courses.app_heads_or_tails.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonJogar.setOnClickListener {
            val intent = Intent(applicationContext, ResultadoActivity::class.java)
            val num = Random().nextInt(500)

            intent.putExtra("number", num)

            startActivity(intent)
        }
    }
}