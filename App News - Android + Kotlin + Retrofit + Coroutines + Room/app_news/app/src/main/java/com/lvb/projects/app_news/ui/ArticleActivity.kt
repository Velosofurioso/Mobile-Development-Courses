package com.lvb.projects.app_news.ui

import android.webkit.WebViewClient
import com.google.android.material.snackbar.Snackbar
import com.lvb.projects.app_news.R
import com.lvb.projects.app_news.model.Article
import com.lvb.projects.app_news.model.data.NewsDataSource
import com.lvb.projects.app_news.presenter.ViewHome
import com.lvb.projects.app_news.presenter.favorite.FavoritePresenter
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AbstractActivity(), ViewHome.Favorite {

    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter

    override fun getLayout(): Int = R.layout.activity_article

    override fun onInject() {
        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(dataSource, this)

        web_view.apply {
            webViewClient = WebViewClient()
            article.url?.let {
                loadUrl(it)
            }
        }

        web_button_fab.setOnClickListener {
            presenter.saveArticle(article)
            Snackbar.make(it, R.string.article_saved_successful, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun getArticle() {
        intent.extras?.let {
            article = it.get("article") as Article
        }
    }

    override fun showArticles(articles: List<Article>) {

    }
}