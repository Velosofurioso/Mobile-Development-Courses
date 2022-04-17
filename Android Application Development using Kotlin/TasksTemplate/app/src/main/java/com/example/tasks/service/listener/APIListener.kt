package com.example.tasks.service.listener

import com.example.tasks.service.model.HeaderModel

interface APIListener <T> {

    fun onSucess(model: T, statusCode: Int)

    fun onFailed(str: String)


}