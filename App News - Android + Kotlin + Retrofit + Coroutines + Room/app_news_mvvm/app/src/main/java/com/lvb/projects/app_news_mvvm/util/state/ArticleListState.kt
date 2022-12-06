package com.lvb.projects.app_news_mvvm.util.state

import com.lvb.projects.app_news_mvvm.data.local.model.Article

sealed class ArticleListState {
    data class Success(val list: List<Article>) : ArticleListState()
    data class ErrorMessage(val errorMessage: String) : ArticleListState()
    object Loading : ArticleListState()
    object Empty : ArticleListState()
}