package com.lvb.projects.app_news_mvvm.ui.fragments.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvb.projects.app_news_mvvm.data.local.model.Article
import com.lvb.projects.app_news_mvvm.repository.NewsRepository
import kotlinx.coroutines.launch

class WebViewViewModel constructor (
    private val repository: NewsRepository
): ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.updateInsert(article)
    }

}