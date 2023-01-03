package com.lvb.projects.app_marvel.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvb.projects.app_marvel.data.model.character.CharacterModel
import com.lvb.projects.app_marvel.data.model.character.CharacterModelResponse
import com.lvb.projects.app_marvel.data.model.comic.ComicModelResponse
import com.lvb.projects.app_marvel.repository.MarvelRepository
import com.lvb.projects.app_marvel.ui.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsCharacterViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {

    private val _details =
        MutableStateFlow<ResourceState<ComicModelResponse>>(ResourceState.Empty())

    val details: StateFlow<ResourceState<ComicModelResponse>> = _details

    fun fetch(characterId: Int) = viewModelScope.launch {
        safeFetch(characterId)
    }

    private suspend fun safeFetch(characterId: Int) {
        _details.value = ResourceState.Loading()
        try {
            val response = repository.getComics(characterId)
            _details.value = handleResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _details.value = ResourceState.Error("Error in network connection")
                else -> _details.value = ResourceState.Error("Failure in data conversion")
            }
        }
    }

    private fun handleResponse(response: Response<ComicModelResponse>): ResourceState<ComicModelResponse> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                return ResourceState.Success(values)
            }
        }
        return ResourceState.Error(response.message())
    }

    fun insert(characterModel: CharacterModel) = viewModelScope.launch {
        repository.insert(characterModel)

    }

}
