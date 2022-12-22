package com.lvb.projects.app_marvel.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lvb.projects.app_marvel.databinding.FragmentFavoriteCharacterBinding
import com.lvb.projects.app_marvel.ui.base.BaseFragment
import com.lvb.projects.app_marvel.ui.details.DetailsCharacterViewModel

class FavoriteCharacterFragment : BaseFragment<FragmentFavoriteCharacterBinding, DetailsCharacterViewModel>() {
    override val viewModel: DetailsCharacterViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteCharacterBinding =
        FragmentFavoriteCharacterBinding.inflate(inflater, container, false)

}