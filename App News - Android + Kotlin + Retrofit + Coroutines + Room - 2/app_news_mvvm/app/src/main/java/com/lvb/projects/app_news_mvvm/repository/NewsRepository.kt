package com.lvb.projects.app_news_mvvm.repository

import androidx.lifecycle.LiveData
import com.lvb.projects.app_news_mvvm.data.local.model.Article
import com.lvb.projects.app_news_mvvm.data.local.db.ArticleDatabase
import com.lvb.projects.app_news_mvvm.data.remote.NewsAPI

class NewsRepository(private val api: NewsAPI, private val db: ArticleDatabase) {


    //Remote
    suspend fun getAllRemote() = api.getBreakingNews()
    suspend fun search(query: String) = api.searchNews(query)


    //Local
    suspend fun updateInsert(article: Article) = db.getArticlesDao().updateInsert(article)
    fun getAll(): LiveData<List<Article>> = db.getArticlesDao().getAll()
    suspend fun delete(article: Article) = db.getArticlesDao().delete(article)
}