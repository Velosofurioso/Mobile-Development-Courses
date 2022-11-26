package com.lvb.projects.app_news_mvp.presenter.favorite

import com.lvb.projects.app_news_mvp.model.Article

interface FavoriteHome {

    interface Presenter {
        fun onSucess(articles: List<Article>)
    }

}