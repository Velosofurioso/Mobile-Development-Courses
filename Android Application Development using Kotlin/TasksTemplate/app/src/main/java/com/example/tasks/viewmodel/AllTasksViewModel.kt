package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.model.TaskModel
import com.example.tasks.service.repository.TaskRepository

class AllTasksViewModel(application: Application) : AndroidViewModel(application) {

    private val mTaskRepository =  TaskRepository(application)

    private val mTask =  MutableLiveData<List<TaskModel>>()
    var tasks: LiveData<List<TaskModel>> =  mTask

    private val mValidation =  MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> =  mValidation

    private var mTaskFilter = 0

    fun list(taskFilter: Int) {
        mTaskFilter = taskFilter
        val listener = object : APIListener<List<TaskModel>> {
            override fun onSucess(model: List<TaskModel>) {
                mTask.value = model
            }

            override fun onFailed(str: String) {
                mTask.value = arrayListOf()
                mValidation.value = ValidationListener(str)
            }

        }
        when (mTaskFilter) {

            TaskConstants.FILTER.ALL -> {
                mTaskRepository.all(listener)
            }

            TaskConstants.FILTER.NEXT -> {
                mTaskRepository.nextWeek(listener)
            }

            else -> {
                mTaskRepository.overdue(listener)
            }
        }

    }

    fun delete(id: Int) {
        mTaskRepository.delete(id, object: APIListener<Boolean>{
            override fun onSucess(model: Boolean) {
                list(mTaskFilter)
                mValidation.value = ValidationListener()
            }

            override fun onFailed(str: String) {
                mValidation.value =ValidationListener(str)
            }

        })
    }



    fun complete(id: Int) {
        updateStatus(id, true)
    }

    fun undo(id: Int) {
        updateStatus(id, false)
    }

    private fun updateStatus(id: Int, complete: Boolean) {
        mTaskRepository.updateStatus(id, complete, object: APIListener<Boolean>{
            override fun onSucess(model: Boolean) {
                list(mTaskFilter)
            }

            override fun onFailed(str: String) {
                TODO("Not yet implemented")
            }

        })
    }


}