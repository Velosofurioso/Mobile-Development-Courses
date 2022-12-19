package com.lvb.projects.app_notes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.lvb.projects.app_notes.NotesAdapter
import com.lvb.projects.app_notes.R

class MainActivity : AppCompatActivity() {

    val notesAdapter: NotesAdapter by lazy {
        NotesAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_add) {
            //TODO call activity to add notes
        }
        return super.onOptionsItemSelected(item)
    }
}