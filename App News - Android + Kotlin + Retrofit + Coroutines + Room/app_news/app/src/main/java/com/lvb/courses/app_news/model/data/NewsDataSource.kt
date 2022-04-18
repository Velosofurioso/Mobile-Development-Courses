package com.lvb.courses.app_news.model.data

import com.lvb.courses.app_news.network.RetrofitInstance
import com.lvb.courses.app_news.presenter.news.NewsHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews("br")

            if(response.isSuccessful) {
                response.body()?.let {newsResponse ->
                    callback.onSuccess(newsResponse)
                }
            }
            else {
                callback.onError(response.message())
            }

            callback.onComplete()
        }
    }
}