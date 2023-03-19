package com.lvb.courses.app_task_list.ui.addTask

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lvb.courses.app_task_list.R
import com.lvb.courses.app_task_list.databinding.ActivityAddTaskBinding
import com.lvb.courses.app_task_list.helper.DAO.tasksDAO.TasksDAO
import com.lvb.courses.app_task_list.model.Task

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private var actualTask: Task? = Task()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)

        setContentView(binding.root)

        actualTask = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("taskSelected", Task::class.java)
        } else {
            intent.getSerializableExtra("taskSelected") as Task
        }

        if (actualTask?.id != null) {
            binding.txtTask.setText(actualTask?.name)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.manu_add_task, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.saveItem -> insertSQLIteDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertSQLIteDb() {
        var operation = getString(R.string.operation_save)
        val task = Task()

        task.name = binding.txtTask.text?.toString()

        if (task.name?.isNotEmpty() == true) {
            val taskDao = TasksDAO(baseContext)

            if (actualTask?.id != null) {
                task.id = actualTask?.id
                operation = getString(R.string.operation_update)

                if (taskDao.update(task)) {
                    Toast.makeText(
                        baseContext,
                        getString(R.string.operation_success, operation),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else
                    Toast.makeText(
                        baseContext,
                        getString(R.string.operation_failed, operation),
                        Toast.LENGTH_SHORT
                    ).show()
            } else {
                if (taskDao.save(task)) {
                    Toast.makeText(
                        baseContext,
                        getString(R.string.operation_success, operation),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else
                    Toast.makeText(
                        baseContext,
                        getString(R.string.operation_failed, operation),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

}
