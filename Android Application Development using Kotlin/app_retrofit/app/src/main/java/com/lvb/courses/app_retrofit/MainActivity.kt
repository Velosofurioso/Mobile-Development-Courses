package com.lvb.courses.app_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val remote = RetrofitClient.createService(PostService::class.java)
        val call : Call<List<PostModel>> = remote.list()

        // Metodo sincrono
        //val response = call.execute()

        // Metodo assincrono
        val response = call.enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                val s = response.body()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                val s = t.message
            }

        })
    }
}