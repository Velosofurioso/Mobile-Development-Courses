package com.lvb.projects.app_notes.data

import androidx.lifecycle.MutableLiveData

class Database {

    private val mDatabase: MutableLiveData<MutableList<Note>> = MutableLiveData()

    init {
        mDatabase.value = mutableListOf()
    }

    fun insertNote(note: Note) {
        mDatabase.apply {
            val tmp = value
            tmp?.add(note)
            postValue(tmp)
        }
    }

    fun getNote() = mDatabase
}