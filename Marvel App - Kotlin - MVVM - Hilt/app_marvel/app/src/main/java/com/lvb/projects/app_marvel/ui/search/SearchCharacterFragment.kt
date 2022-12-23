package com.lvb.projects.app_marvel.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lvb.projects.app_marvel.databinding.FragmentSearchCharacterBinding
import com.lvb.projects.app_marvel.ui.base.BaseFragment
import com.lvb.projects.app_marvel.ui.details.DetailsCharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCharacterFragment : BaseFragment<FragmentSearchCharacterBinding, DetailsCharacterViewModel>() {
    override val viewModel: DetailsCharacterViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchCharacterBinding =
        FragmentSearchCharacterBinding.inflate(inflater, container, false)

}