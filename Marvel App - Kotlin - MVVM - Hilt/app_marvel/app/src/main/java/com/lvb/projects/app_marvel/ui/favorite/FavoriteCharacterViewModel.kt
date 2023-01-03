package com.lvb.projects.app_marvel.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvb.projects.app_marvel.data.model.character.CharacterModel
import com.lvb.projects.app_marvel.repository.MarvelRepository
import com.lvb.projects.app_marvel.ui.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCharacterViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {

    private val _favorites =
        MutableStateFlow<ResourceState<List<CharacterModel>>>(ResourceState.Empty())
    val favorites: StateFlow<ResourceState<List<CharacterModel>>> = _favorites

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        repository.getAll().collectLatest { characters ->
            if (characters.isNullOrEmpty()) {
                _favorites.value = ResourceState.Empty()
            } else {
                _favorites.value = ResourceState.Success(characters)
            }

        }
    }

    fun delete (characterModel: CharacterModel) = viewModelScope.launch {
        repository.delete(characterModel)
    }


}
