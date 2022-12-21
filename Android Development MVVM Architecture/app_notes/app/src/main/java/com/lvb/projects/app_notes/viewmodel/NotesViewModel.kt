package com.lvb.projects.app_notes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lvb.projects.app_notes.data.Database
import com.lvb.projects.app_notes.data.Note
import com.lvb.projects.app_notes.data.NotesManager

class NotesViewModel(private val notesManager: NotesManager): ViewModel() {

    private var mNotes: MutableLiveData<MutableList<Note>>? = null

    fun getNotes(): MutableLiveData<MutableList<Note>> {
        if(mNotes == null) {
            mNotes = notesManager.getNotes()
        }
        return mNotes!!
    }

    fun save(mNote: Note) {
        notesManager.addNote(mNote)
    }

}