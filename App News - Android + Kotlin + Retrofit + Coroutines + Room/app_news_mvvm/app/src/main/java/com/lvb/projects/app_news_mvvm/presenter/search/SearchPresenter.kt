package com.lvb.projects.app_news_mvvm.presenter.search

import com.lvb.projects.app_news_mvvm.data.local.model.NewsResponse
import com.lvb.projects.app_news_mvvm.repository.NewsDataSource
import com.lvb.projects.app_news_mvvm.presenter.ViewHome

class SearchPresenter(val view: ViewHome.View, private val dataSource: NewsDataSource): SearchHome.Presenter {

    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.searchNews(term, this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}