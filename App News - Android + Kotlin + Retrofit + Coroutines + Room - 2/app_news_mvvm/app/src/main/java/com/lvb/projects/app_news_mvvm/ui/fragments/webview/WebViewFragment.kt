package com.lvb.projects.app_news_mvvm.ui.fragments.webview

import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.lvb.projects.app_news_mvvm.R
import com.lvb.projects.app_news_mvvm.data.local.db.ArticleDatabase
import com.lvb.projects.app_news_mvvm.data.local.model.Article
import com.lvb.projects.app_news_mvvm.data.remote.RetrofitInstance
import com.lvb.projects.app_news_mvvm.databinding.FragmentWebViewBinding
import com.lvb.projects.app_news_mvvm.repository.NewsRepository
import com.lvb.projects.app_news_mvvm.ui.fragments.base.BaseFragment

class WebViewFragment : BaseFragment<WebViewViewModel, FragmentWebViewBinding>() {

    private val  args: WebViewFragmentArgs by navArgs()
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.save_articles -> {
                viewModel.saveArticle(article)
                Toast.makeText(requireContext(), "Article Save with Success", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewModel(): Class<WebViewViewModel> = WebViewViewModel::class.java

    override fun getFragmentRepository(): NewsRepository = NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))

    override fun getFragmentBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWebViewBinding = FragmentWebViewBinding.inflate(inflater, container, false)
}