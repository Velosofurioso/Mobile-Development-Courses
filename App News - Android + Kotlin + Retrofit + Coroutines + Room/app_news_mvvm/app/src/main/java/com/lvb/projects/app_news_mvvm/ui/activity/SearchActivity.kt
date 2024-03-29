package com.lvb.projects.app_news_mvvm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvb.projects.app_news_mvvm.ui.adapter.MainAdapter
import com.lvb.projects.app_news_mvvm.data.local.model.Article
import com.lvb.projects.app_news_mvvm.databinding.ActivitySearchBinding
import com.lvb.projects.app_news_mvvm.repository.NewsDataSource
import com.lvb.projects.app_news_mvvm.presenter.ViewHome
import com.lvb.projects.app_news_mvvm.presenter.search.SearchPresenter
import com.lvb.projects.app_news_mvvm.util.UtilQueryTextListener


class SearchActivity : AppCompatActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var binding: ActivitySearchBinding
    private lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataSource = NewsDataSource(this)
        presenter = SearchPresenter(this, dataSource)
        configRecycle()
        search()
        clickAdapter()
    }


    private fun search() {
        binding.searchNews.setOnQueryTextListener(
            UtilQueryTextListener(this.lifecycle) { newsText ->
                newsText?.let { query ->
                    if (query.isNotEmpty()) {
                        presenter.search(query)
                        binding.searchProgressBar.visibility = View.VISIBLE
                    }
                }

            }
        )
    }

    private fun configRecycle() {
        with(binding.rvSearch) {
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
        binding.searchProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        binding.searchProgressBar.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }
}