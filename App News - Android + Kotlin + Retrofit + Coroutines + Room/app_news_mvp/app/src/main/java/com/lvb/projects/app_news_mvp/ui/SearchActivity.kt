package com.lvb.projects.app_news_mvp.ui

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvb.projects.app_news_mvp.R
import com.lvb.projects.app_news_mvp.adapter.MainAdapter
import com.lvb.projects.app_news_mvp.model.Article
import com.lvb.projects.app_news_mvp.model.data.NewsDataSource
import com.lvb.projects.app_news_mvp.presenter.ViewHome
import com.lvb.projects.app_news_mvp.presenter.search.SearchPresenter
import com.lvb.projects.app_news_mvp.util.UtilQueryTextListener
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AbstractActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: SearchPresenter

    override fun getLayout(): Int = R.layout.activity_search

    override fun onInject() {
        val dataSource = NewsDataSource(this)
        presenter = SearchPresenter(this, dataSource)
        configRecycle()
        search()
        clickAdapter()
    }

    private fun search() {
        search_news.setOnQueryTextListener(
            UtilQueryTextListener(this.lifecycle) { newsText ->
                newsText?.let { query ->
                    if (query.isNotEmpty()) {
                        presenter.search(query)
                        search_progress_bar.visibility = View.VISIBLE
                    }
                }

            }
        )
    }

    private fun configRecycle() {
        with(rv_search) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(DividerItemDecoration(this@SearchActivity, DividerItemDecoration.VERTICAL))
        }
    }

    private fun clickAdapter() {
        mainAdapter.setOnClickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    override fun showProgressBar() {
        search_progress_bar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        search_progress_bar.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }
}