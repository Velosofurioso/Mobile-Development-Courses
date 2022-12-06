package com.lvb.projects.app_news_mvvm.data.local.model

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)