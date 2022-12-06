package com.lvb.projects.app_news_mvvm.ui.fragments.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lvb.projects.app_news_mvvm.repository.NewsRepository
import com.lvb.projects.app_news_mvvm.ui.fragments.favorite.FavoriteViewModel
import com.lvb.projects.app_news_mvvm.ui.fragments.home.HomeViewModel
import com.lvb.projects.app_news_mvvm.ui.fragments.search.SearchViewModel
import com.lvb.projects.app_news_mvvm.ui.fragments.webview.WebViewViewModel

class ViewModelFactory(
    private val repository: NewsRepository,
    private val application: Application
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom((HomeViewModel::class.java)) -> HomeViewModel(repository, application) as T
            modelClass.isAssignableFrom((SearchViewModel::class.java)) -> SearchViewModel(repository) as T
            modelClass.isAssignableFrom((FavoriteViewModel::class.java)) -> FavoriteViewModel(repository) as T
            modelClass.isAssignableFrom((WebViewViewModel::class.java)) -> WebViewViewModel(repository) as T
            else -> throw java.lang.IllegalArgumentException("ViewModel not Found")
        }
    }
}