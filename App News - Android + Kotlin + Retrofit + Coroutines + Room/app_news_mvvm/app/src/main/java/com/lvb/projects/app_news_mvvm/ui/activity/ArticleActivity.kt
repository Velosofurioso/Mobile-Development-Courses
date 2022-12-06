package com.lvb.projects.app_news_mvvm.ui.activity

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.lvb.projects.app_news_mvvm.R
import com.lvb.projects.app_news_mvvm.databinding.ActivityArticleBinding
import com.lvb.projects.app_news_mvvm.data.local.model.Article
import com.lvb.projects.app_news_mvvm.repository.NewsDataSource
import com.lvb.projects.app_news_mvvm.presenter.ViewHome
import com.lvb.projects.app_news_mvvm.presenter.favorite.FavoritePresenter

class ArticleActivity : AppCompatActivity(), ViewHome.Favorite {

    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(dataSource, this)

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let {
                loadUrl(it)
            }
        }

        binding.webButtonFab.setOnClickListener {
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