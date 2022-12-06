package com.lvb.projects.app_news_mvvm.presenter.search

import com.lvb.projects.app_news_mvvm.data.local.model.NewsResponse

interface SearchHome {

    interface Presenter {
        fun search(term: String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}