package com.lvb.projects.app_news_mvp.model.data

import com.lvb.projects.app_news_mvp.model.Article
import com.lvb.projects.app_news_mvp.model.db.ArticleDatabase

class NewsRepository(private val db: ArticleDatabase) {

    suspend fun updateInsert(article: Article) = db.getArticlesDao().updateInsert(article)

    fun getAll(): List<Article> = db.getArticlesDao().getAll()

    suspend fun delete(article: Article) = db.getArticlesDao().delete(article)
}