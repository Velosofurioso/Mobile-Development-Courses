package com.lvb.projects.app_news_mvvm.presenter.favorite

import com.lvb.projects.app_news_mvvm.data.local.model.Article

interface FavoriteHome {

    interface Presenter {
        fun onSucess(articles: List<Article>)
    }

}