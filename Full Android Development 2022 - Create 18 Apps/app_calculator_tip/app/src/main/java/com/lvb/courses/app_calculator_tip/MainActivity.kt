package com.lvb.courses.app_calculator_tip

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lvb.courses.app_calculator_tip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var percentage = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekBarGorjeta.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                percentage = progress
                binding.textPorcentangem.text = Math.round(percentage.toDouble()).toString() + "%"
                calculate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    fun calculate() {
        val valueRecovered: String = binding.editValor.text.toString()

        if ("" == valueRecovered) {
            Toast.makeText(
                applicationContext,
                "Enter a value",
                Toast.LENGTH_SHORT
            ).show()
        }
        else {
            // Convert String value to Double
            val valueTyped = java.lang.Double.valueOf(valueRecovered)

            // Calculate the tip amount
            val valueTip: Double = valueTyped * (100 / percentage)
            val amount = valueTyped + valueTip
            binding.textGorjeta.text = "R$ " + Math.round(valueTip)
            binding.textTotal.text = "R$ " + Math.round(amount)
        }
    }
}