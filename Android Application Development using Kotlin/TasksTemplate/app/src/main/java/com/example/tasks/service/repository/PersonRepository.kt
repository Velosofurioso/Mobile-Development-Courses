package com.example.tasks.service.repository

import android.content.Context
import com.example.tasks.R
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.repository.remote.PersonService
import com.example.tasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository(val context: Context) : BaseRepository(context){

    private val mRemote = RetrofitClient.createService(PersonService::class.java)

    fun login(email: String, password: String, listener: APIListener<HeaderModel>) {

        // Verificação de internet
        if (!isConnectionAvailable(mContext)) {
            listener.onFailed(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        val call: Call<HeaderModel> = mRemote.login(email, password)
        // Assincrona
        call.enqueue(object : Callback<HeaderModel> {
            override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {
                if(!response.isSuccessful) {
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailed(validation)
                }
                else {
                    response.body()?.let { listener.onSucess(it) }
                }
            }

            override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                listener.onFailed(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun create(name: String, email: String, password: String, listener: APIListener<HeaderModel>) {

        // Verificação de internet
        if (!isConnectionAvailable(mContext)) {
            listener.onFailed(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        
        val call: Call<HeaderModel> = mRemote.create(name, email, password)
        // Assincrona
        call.enqueue(object : Callback<HeaderModel> {
            override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {
                if(!response.isSuccessful) {
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailed(validation)
                }
                else {
                    response.body()?.let { listener.onSucess(it) }
                }
            }

            override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                listener.onFailed(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

}