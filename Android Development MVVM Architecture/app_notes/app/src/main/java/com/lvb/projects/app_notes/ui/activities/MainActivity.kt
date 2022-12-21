package com.lvb.projects.app_notes.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.lvb.projects.app_notes.R
import com.lvb.projects.app_notes.data.Note
import com.lvb.projects.app_notes.ui.NotesAdapter
import com.lvb.projects.app_notes.viewmodel.NotesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val notesViewModel: NotesViewModel by viewModel()

    private val notesAdapter: NotesAdapter by lazy {
        NotesAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_notes)

        recyclerView.adapter = notesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        notesViewModel.getNotes().observe(this) { data ->
            data?.let {
                if(it.isEmpty()) {
                    Toast.makeText(this, "Empty List", Toast.LENGTH_SHORT).show()
                } else {
                    notesAdapter.add(it)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add) {
            dialogAddNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dialogAddNote() {
        val layout = LayoutInflater.from(this).inflate(R.layout.dialog_ui, null, false)

        val inputNote = layout.findViewById<TextInputEditText>(R.id.input_note)

        val dialog = AlertDialog.Builder(this).apply {
            setView(layout)
            setNegativeButton("Cancel", null)
            setPositiveButton("Save") {d, i ->
                // Save Note
                val note = Note(0, inputNote.text.toString())
                notesViewModel.save(note)
            }
        }
        dialog.create().show()
    }
}