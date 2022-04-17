package com.example.tasks.service.listener

class ValidationListener(message: String = "") {

    private var mStatus: Boolean = true
    private var mMessage: String = ""

    init {

        if(message.isNotEmpty()) {
            mStatus = false
            mMessage = message
        }

    }

    fun sucess() = mStatus
    fun errorMessage() = mMessage

}