package com.lvb.projects.app_notes.data

class NotesManager(private val database: Database) {

    fun getNotes() = database.getNote()

    fun addNote(mNote: Note) = database.insertNote(mNote)
}