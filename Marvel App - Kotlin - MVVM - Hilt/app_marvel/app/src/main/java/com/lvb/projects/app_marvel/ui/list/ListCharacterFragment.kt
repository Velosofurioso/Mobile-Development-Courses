package com.lvb.projects.app_marvel.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lvb.projects.app_marvel.databinding.FragmentListCharacterBinding
import com.lvb.projects.app_marvel.ui.base.BaseFragment
import com.lvb.projects.app_marvel.ui.details.DetailsCharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCharacterFragment : BaseFragment<FragmentListCharacterBinding, DetailsCharacterViewModel>() {
    override val viewModel: DetailsCharacterViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListCharacterBinding =
        FragmentListCharacterBinding.inflate(inflater, container, false)

}