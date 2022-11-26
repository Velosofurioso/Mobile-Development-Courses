package com.lvb.projects.app_news_mvp.presenter.news

import com.lvb.projects.app_news_mvp.model.NewsResponse

interface NewsHome {

    interface Presenter {
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}