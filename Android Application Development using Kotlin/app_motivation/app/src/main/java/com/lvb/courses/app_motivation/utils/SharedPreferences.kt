package com.lvb.courses.app_motivation.utils

import android.content.Context
import android.content.SharedPreferences


class SharedPreferences (context: Context) {

    private val prefsName: SharedPreferences? = context.getSharedPreferences("userName", Context.MODE_PRIVATE)

    fun storeString(key : String, value : String) {
        prefsName?.edit()?.putString(key, value)?.apply()
    }

    fun getString(key : String) : String {
        return prefsName?.getString(key, "") ?: ""
    }

}