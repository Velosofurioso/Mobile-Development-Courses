package com.lvb.projects.app_news_mvvm.ui.fragments.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvb.projects.app_news_mvvm.data.local.model.NewsResponse
import com.lvb.projects.app_news_mvvm.repository.NewsRepository
import com.lvb.projects.app_news_mvvm.util.InternetChecker
import com.lvb.projects.app_news_mvvm.util.state.StateResource
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel constructor(
    private val  repository: NewsRepository,
    context: Application
) : AndroidViewModel(context) {

    private val _getAll = MutableLiveData<StateResource<NewsResponse>>()
    val getAll: LiveData<StateResource<NewsResponse>> get() = _getAll

    init {
        safeFetchAll()
    }

    private fun safeFetchAll() = viewModelScope.launch {
        _getAll.value = StateResource.Loading()
        try {
            if (InternetChecker.checkForInternetConnection(getApplication())) {
                val response = repository.getAllRemote()
                _getAll.value = handleResponse(response)
            } else {
                _getAll.value = StateResource.Error("No Connection with Internet")
            }


        } catch(e: Exception) {
            _getAll.value = StateResource.Error("Articles not Found: ${e.message}")
        }
    }

    private fun handleResponse(response: Response<NewsResponse>): StateResource<NewsResponse>? {
        if(response.isSuccessful) {
            response.body()?.let { values ->
                return StateResource.Success(values)
            }
        }
        return StateResource.Error(response.message())

    }

}