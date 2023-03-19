package com.lvb.courses.app_task_list.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.lvb.courses.app_task_list.util.Constants


class DBHelper(
    private val context: Context
) : SQLiteOpenHelper(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS " + Constants.TASKS_TABLE+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL); "

        try {
            db?.execSQL(sql)
        }
        catch (e: Exception) {
            Log.e("ERROR DB", "Error to create table" + e.message)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
}