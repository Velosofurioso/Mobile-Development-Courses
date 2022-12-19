package com.lvb.projects.app_notes.data

class NotesManager {

    private val data = listOf<Note>(
        Note(id = 0, text = "Note 1"),
        Note(id = 0, text = "Note 2"),
        Note(id = 0, text = "Note 3"),
        Note(id = 0, text = "Note 4"),
        Note(id = 0, text = "Note 5"),
    )


    fun getNotes() = data
}