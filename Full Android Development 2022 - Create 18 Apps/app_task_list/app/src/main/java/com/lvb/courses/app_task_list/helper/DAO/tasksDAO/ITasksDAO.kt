package com.lvb.courses.app_task_list.helper.DAO.tasksDAO

import com.lvb.courses.app_task_list.model.Task

interface ITasksDAO {

    fun save(task: Task) : Boolean
    fun update(task: Task) : Boolean
    fun delete(task: Task) : Boolean
    fun list(): MutableList<Task>
}