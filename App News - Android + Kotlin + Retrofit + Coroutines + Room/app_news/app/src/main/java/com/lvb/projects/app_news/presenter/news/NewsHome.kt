package com.lvb.projects.app_news.presenter.news

import com.lvb.projects.app_news.model.NewsResponse

interface NewsHome {

    interface Presenter {
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}