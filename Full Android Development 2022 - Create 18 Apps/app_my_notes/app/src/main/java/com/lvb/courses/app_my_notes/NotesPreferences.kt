package com.lvb.courses.app_my_notes

import android.content.Context
import android.content.SharedPreferences

class NotesPreferences(private val context: Context) {

    private var preferences: SharedPreferences
    private var preferencesEditor: SharedPreferences.Editor

    private val NAME_FILE = "notes.preferences"
    private val KEY_NAME = "name"

    init {
        preferences = this.context.getSharedPreferences(NAME_FILE, 0)
        preferencesEditor = preferences.edit()
    }


    fun saveNote(note: String) {
        this.preferencesEditor.putString(KEY_NAME, note)
        this.preferencesEditor.commit()

    }

    fun getNote(): String {
        return preferences.getString("name","") ?: ""
    }
}