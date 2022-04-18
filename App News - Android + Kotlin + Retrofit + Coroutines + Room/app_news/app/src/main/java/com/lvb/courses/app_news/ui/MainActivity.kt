package com.lvb.courses.app_news.ui

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lvb.courses.app_news.R
import com.lvb.courses.app_news.adapter.MainAdapter
import com.lvb.courses.app_news.model.Article
import com.lvb.courses.app_news.model.data.NewsDataSource
import com.lvb.courses.app_news.presenter.ViewHome
import com.lvb.courses.app_news.presenter.news.NewsPresenter

class MainActivity : AbstractActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: NewsPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        configRecycle()
    }

    private fun configRecycle() {
        with(findViewById<RecyclerView>(R.id.rc_news)) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(applicationContext)
            addItemDecoration(DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            ))

        }
    }

    override fun showProgressBar() {
        findViewById<ProgressBar>(R.id.progress_list_data).visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideProgressBar() {
        findViewById<ProgressBar>(R.id.progress_list_data).visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search_menu -> {
                Intent(this, SearchActivity::class.java).apply {
                    startActivity(this)
                }
            }

            R.id.favorite_menu -> {
                Intent(this, FavoriteActivity::class.java).apply {
                    startActivity(this)
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }
}