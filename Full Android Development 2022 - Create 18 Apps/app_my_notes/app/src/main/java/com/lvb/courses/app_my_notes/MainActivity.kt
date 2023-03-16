package com.lvb.courses.app_my_notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.lvb.courses.app_my_notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val preferences: NotesPreferences by lazy {
        NotesPreferences(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kotlin
        supportActionBar?.hide()

        binding.fab.setOnClickListener { view ->
            //binding.fragment.editTextTextMultiLine.focusable = View.FOCUSABLE

            binding.fragment.editTextTextMultiLine.text?.let {
                if (it.toString() == "") {
                    Snackbar.make(view, "Type a note first", Snackbar.LENGTH_LONG).show()
                } else {
                    preferences.saveNote(it.toString())
                    Snackbar.make(view, "Annotation successfully saved", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }

        preferences.getNote().let {
            if (it != "")
                binding.fragment.editTextTextMultiLine.setText(it)
        }
    }
}