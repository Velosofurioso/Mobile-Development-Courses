package com.example.tasks.service.repository

import android.content.Context
import com.example.tasks.R
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.PriorityModel
import com.example.tasks.service.repository.local.TaskDatabase
import com.example.tasks.service.repository.remote.PriorityService
import com.example.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(val context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(PriorityService::class.java)
    private val mPriorityDatabase = TaskDatabase.getDatabase(context).priorityDAO()

    fun all() {
        val call: Call<List<PriorityModel>> = mRemote.list()
        call.enqueue(object : Callback<List<PriorityModel>> {
            override fun onResponse(
                call: Call<List<PriorityModel>>,
                response: Response<List<PriorityModel>>
            ) {
                if(!response.isSuccessful) {
                    mPriorityDatabase.clear()
                    response.body()?.let { mPriorityDatabase.save(it) }
                }
            }

            override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun all(listener: APIListener<List<PriorityModel>>) {

        // Verificação de internet
        if (!isConnectionAvailable(mContext)) {
            listener.onFailed(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        val call: Call<List<PriorityModel>> = mRemote.list()
        call.enqueue(object : Callback<List<PriorityModel>> {
            override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
                listener.onFailed(mContext.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<List<PriorityModel>>,
                response: Response<List<PriorityModel>>
            ) {
                val code = response.code()
                if (fail(code)) {
                    listener.onFailed(failRespose(response.errorBody()!!.string()))
                } else {
                    response.body()?.let { listener.onSucess(it, response.code()) }
                }
            }

        })
    }

    fun list() = mPriorityDatabase.list()

    fun save(list: List<PriorityModel>) {
        mPriorityDatabase.clear()
        mPriorityDatabase.save(list)
    }

    fun getDescption(id: Int) = mPriorityDatabase.getDescription(id)
}