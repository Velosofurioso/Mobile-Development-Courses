package com.lvb.courses.app_heads_or_tails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.lvb.courses.app_heads_or_tails.R
import com.lvb.courses.app_heads_or_tails.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultadoBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.buttonVoltar.setOnClickListener {
            finish()
        }
        loadDataFromActivity()
    }

    private fun loadDataFromActivity() {
        val extras = intent.extras
        if (extras != null && !extras.isEmpty) {
            if (extras.getInt("number") <= 250) {
                binding.imageResult.setImageResource(R.drawable.moeda_cara)
            } else {
                binding.imageResult.setImageResource(R.drawable.moeda_coroa)
            }
        }
    }
}