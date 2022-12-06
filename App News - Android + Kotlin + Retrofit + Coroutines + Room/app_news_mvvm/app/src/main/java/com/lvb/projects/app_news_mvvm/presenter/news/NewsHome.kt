package com.lvb.projects.app_news_mvvm.presenter.news

import com.lvb.projects.app_news_mvvm.data.local.model.NewsResponse

interface NewsHome {

    interface Presenter {
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}