package com.lvb.projects.app_news_mvvm.presenter.news

import com.lvb.projects.app_news_mvvm.data.local.model.NewsResponse
import com.lvb.projects.app_news_mvvm.repository.NewsDataSource
import com.lvb.projects.app_news_mvvm.presenter.ViewHome

class NewsPresenter(val view: ViewHome.View, private val dataSource: NewsDataSource) : NewsHome.Presenter {

    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)
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