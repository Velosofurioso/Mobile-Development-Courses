package com.lvb.projects.app_notes.data

import androidx.lifecycle.MutableLiveData

class NotesManager {

    private val data: MutableLiveData<MutableList<Note>> = MutableLiveData()

    init {
        data.value = mutableListOf()
    }

    fun getNotes() = data

    fun addNote(mNote: Note) = data.apply {
        val tmp = value
        tmp?.add(mNote)
        postValue(tmp)
    }
}