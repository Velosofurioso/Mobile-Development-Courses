package com.lvb.projects.app_news_mvvm.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvb.projects.app_news_mvvm.data.local.model.NewsResponse
import com.lvb.projects.app_news_mvvm.repository.NewsRepository
import com.lvb.projects.app_news_mvvm.util.state.StateResource
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel constructor(
    private val repository: NewsRepository
): ViewModel() {

    private val _search = MutableLiveData<StateResource<NewsResponse>>()
    val search: LiveData<StateResource<NewsResponse>> get() = _search

    fun fetchSearch(query: String) = viewModelScope.launch {
        safeFetchSearch(query)
    }

    private suspend fun safeFetchSearch(query: String) {
        _search.value = StateResource.Loading()
        try {
            val response = repository.search(query = query)
            _search.value = handleResponse(response)
        } catch(e: Exception) {
            _search.value = StateResource.Error("Articles not Found: ${e.message}")
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