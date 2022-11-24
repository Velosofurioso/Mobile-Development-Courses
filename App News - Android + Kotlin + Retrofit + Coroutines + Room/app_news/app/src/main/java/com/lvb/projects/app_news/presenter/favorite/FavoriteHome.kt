package com.lvb.projects.app_news.presenter.favorite

import com.lvb.projects.app_news.model.Article

interface FavoriteHome {

    interface Presenter {
        fun onSucess(articles: List<Article>)
    }

}