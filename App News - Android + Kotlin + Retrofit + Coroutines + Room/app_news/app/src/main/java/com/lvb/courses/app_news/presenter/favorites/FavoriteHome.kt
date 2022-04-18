package com.lvb.courses.app_news.presenter.favorites

import com.lvb.courses.app_news.model.Article

interface FavoriteHome {

    fun shoArticles(articles: List<Article>)
}