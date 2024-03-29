package com.lvb.projects.app_news_mvp.model.data

import android.content.Context
import com.lvb.projects.app_news_mvp.model.Article
import com.lvb.projects.app_news_mvp.model.db.ArticleDatabase
import com.lvb.projects.app_news_mvp.network.RetrofitInstance
import com.lvb.projects.app_news_mvp.presenter.favorite.FavoriteHome
import com.lvb.projects.app_news_mvp.presenter.news.NewsHome
import com.lvb.projects.app_news_mvp.presenter.search.SearchHome
import kotlinx.coroutines.*

class NewsDataSource(context: Context) {

    private var db: ArticleDatabase = ArticleDatabase(context)
    private var newsRepository: NewsRepository = NewsRepository(db)

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews("br")

            if(response.isSuccessful) {
                response.body()?.let {newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            }
            else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun searchNews(term: String, callback: SearchHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.searchNews(term)

            if(response.isSuccessful) {
                response.body()?.let {
                    callback.onSuccess(it)
                }
                callback.onComplete()
            }
            else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }

    }

    fun saveArticle(article: Article) {
        GlobalScope.launch(Dispatchers.Main) {
            newsRepository.updateInsert(article)
        }
    }

    fun getAllArticles(callback: FavoriteHome.Presenter) {
        var allArticles: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticles = newsRepository.getAll()

            withContext(Dispatchers.Main) {
                callback.onSucess(allArticles)
            }
        }
    }

    fun deleteArticle(article: Article?) {
        GlobalScope.launch(Dispatchers.Main) {
            article?.let { articleSafe ->
                newsRepository.delete(articleSafe)
            }
        }
    }
}