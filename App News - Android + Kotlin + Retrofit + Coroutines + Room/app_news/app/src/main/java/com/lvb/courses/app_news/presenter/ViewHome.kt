package com.lvb.courses.app_news.presenter

import com.lvb.courses.app_news.model.Article

interface ViewHome {

    interface View {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showArticles(articles: List<Article>)


    }
}