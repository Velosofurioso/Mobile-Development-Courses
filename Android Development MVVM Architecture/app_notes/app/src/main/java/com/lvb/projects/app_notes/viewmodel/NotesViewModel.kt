package com.lvb.projects.app_notes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lvb.projects.app_notes.data.Note
import com.lvb.projects.app_notes.data.NotesManager

class NotesViewModel: ViewModel() {

    private val notesManager =  NotesManager()
    private val mNotes = MutableLiveData<List<Note>>()

    fun getNotes(): MutableLiveData<List<Note>> = mNotes

    fun loadNotes() {
        val tmp = notesManager.getNotes()
        mNotes.postValue(tmp)
    }




}