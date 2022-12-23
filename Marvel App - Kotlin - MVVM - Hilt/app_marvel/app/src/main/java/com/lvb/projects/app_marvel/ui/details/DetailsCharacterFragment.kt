package com.lvb.projects.app_marvel.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lvb.projects.app_marvel.databinding.FragmentDetailsCharacterBinding
import com.lvb.projects.app_marvel.databinding.FragmentFavoriteCharacterBinding
import com.lvb.projects.app_marvel.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsCharacterFragment : BaseFragment<FragmentDetailsCharacterBinding, DetailsCharacterViewModel>() {
    override val viewModel: DetailsCharacterViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsCharacterBinding =
        FragmentDetailsCharacterBinding.inflate(inflater, container, false)

}