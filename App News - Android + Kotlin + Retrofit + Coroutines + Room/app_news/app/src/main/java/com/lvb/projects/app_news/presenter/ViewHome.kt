package com.lvb.projects.app_news.presenter

import com.lvb.projects.app_news.model.Article

interface ViewHome {

    interface View {

        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showArticles(articles: List<Article>)
    }


    interface Favorite {
        fun showArticles(articles: List<Article>)
    }
}