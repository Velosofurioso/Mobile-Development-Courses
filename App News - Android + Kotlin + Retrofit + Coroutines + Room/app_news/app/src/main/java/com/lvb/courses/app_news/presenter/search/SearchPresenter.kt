package com.lvb.courses.app_news.presenter.search

import com.lvb.courses.app_news.model.NewsResponse
import com.lvb.courses.app_news.model.data.NewsDataSource
import com.lvb.courses.app_news.presenter.ViewHome

class SearchPresenter(val view: ViewHome.View, private val dataSource: NewsDataSource) : SearchHome.Presenter {

    override fun search(term: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }
}