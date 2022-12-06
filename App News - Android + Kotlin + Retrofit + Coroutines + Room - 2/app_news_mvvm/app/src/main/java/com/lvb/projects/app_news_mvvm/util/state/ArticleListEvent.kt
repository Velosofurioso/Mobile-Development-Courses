package com.lvb.projects.app_news_mvvm.util.state

sealed class ArticleListEvent {
    object Fetch : ArticleListEvent()
}