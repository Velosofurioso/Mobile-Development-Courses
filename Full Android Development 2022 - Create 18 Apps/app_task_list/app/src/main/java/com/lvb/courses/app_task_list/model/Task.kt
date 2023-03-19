package com.lvb.courses.app_task_list.model

import java.io.Serializable

data class Task(
    var id: Long? = null,
    var name: String? = null

) : Serializable
