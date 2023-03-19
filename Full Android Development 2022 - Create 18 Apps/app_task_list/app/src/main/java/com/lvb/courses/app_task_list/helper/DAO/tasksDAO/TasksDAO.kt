package com.lvb.courses.app_task_list.helper.DAO.tasksDAO

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.lvb.courses.app_task_list.helper.DBHelper
import com.lvb.courses.app_task_list.model.Task
import com.lvb.courses.app_task_list.util.Constants

class TasksDAO(val context: Context) : ITasksDAO {

    private val dbHelper: DBHelper by lazy { DBHelper(context) }

    private val reader: SQLiteDatabase = dbHelper.readableDatabase
    private val writer: SQLiteDatabase = dbHelper.writableDatabase

    override fun save(task: Task): Boolean {

        val values = ContentValues()
        values.put("name", task.name)

        return try {
            writer.insert(Constants.TASKS_TABLE, null, values)
            true
        } catch (e: Exception) {
            Log.e("Error", "Error to save task: " + e.message)
            false
        }
    }

    override fun update(task: Task): Boolean {

        val values = ContentValues()
        values.put("name", task.name)


        return try {
            writer.update(Constants.TASKS_TABLE, values, "id=?", arrayOf(task.id.toString()))
            true
        } catch (e: Exception) {
            Log.e("Error", "Error to update task: " + e.message)
            false
        }
    }

    override fun delete(task: Task): Boolean {
        val values = ContentValues()
        values.put("name", task.name)

        return try {
            writer.delete(Constants.TASKS_TABLE, "id=?", arrayOf(task.id.toString()))
            true
        } catch (e: Exception) {
            Log.e("Error", "Error to delete task: " + e.message)
            false
        }
    }

    override fun list(): MutableList<Task> {
        val tasks = mutableListOf<Task>()

        val sql = "SELECT * FROM " + Constants.TASKS_TABLE + " ; "

        val cursor = writer.rawQuery(sql, null)

        while (cursor.moveToNext()) {

            val task = Task()

            val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))

            task.id = id
            task.name = name

            tasks.add(task)
        }
        cursor.close()
        return tasks
    }
}