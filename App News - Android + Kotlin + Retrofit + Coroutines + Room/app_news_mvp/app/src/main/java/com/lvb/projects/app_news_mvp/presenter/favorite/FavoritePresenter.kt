package com.lvb.projects.app_news_mvp.presenter.favorite

import com.lvb.projects.app_news_mvp.model.Article
import com.lvb.projects.app_news_mvp.model.data.NewsDataSource
import com.lvb.projects.app_news_mvp.presenter.ViewHome

class FavoritePresenter(private val dataSource: NewsDataSource, val view: ViewHome.Favorite): FavoriteHome.Presenter {

    fun saveArticle(article: Article) {
        this.dataSource.saveArticle(article)
    }

    override fun onSucess(articles: List<Article>) {
        this.view.showArticles(articles)
    }

    fun getAll() {
        this.dataSource.getAllArticles(this)
    }

    fun deleteArticle(article: Article) {
        this.dataSource.deleteArticle(article)
    }
}