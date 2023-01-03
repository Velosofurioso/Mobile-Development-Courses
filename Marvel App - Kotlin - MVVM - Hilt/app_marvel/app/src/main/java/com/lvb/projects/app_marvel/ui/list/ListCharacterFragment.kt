package com.lvb.projects.app_marvel.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvb.projects.app_marvel.R
import com.lvb.projects.app_marvel.databinding.FragmentListCharacterBinding
import com.lvb.projects.app_marvel.ui.adapters.CharacterAdapter
import com.lvb.projects.app_marvel.ui.base.BaseFragment
import com.lvb.projects.app_marvel.ui.state.ResourceState
import com.lvb.projects.app_marvel.util.hide
import com.lvb.projects.app_marvel.util.show
import com.lvb.projects.app_marvel.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ListCharacterFragment :
    BaseFragment<FragmentListCharacterBinding, ListCharacterViewModel>() {
    override val viewModel: ListCharacterViewModel by viewModels()
    private val characterAdapter by lazy { CharacterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        clickAdapter()
        collectObserver()
    }

    private fun collectObserver() = lifecycleScope.launch {
        viewModel.list.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { values ->
                        binding.progressCircular.hide()
                        characterAdapter.characters = values.data.results.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressCircular.hide()
                    resource.message?.let {
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag("ListCharacterFragment").e("Error -> $it")
                    }
                }
                is ResourceState.Loading -> {
                    binding.progressCircular.show()
                }
                else -> {}
            }

        }
    }

    private fun clickAdapter() {
        characterAdapter.setOnCLickListener { characterModel ->
            val action =
                ListCharacterFragmentDirections.actionListCharacterFragmentToDetailsCharacterFragment(
                    characterModel
                )
            findNavController().navigate(action)
        }
    }

    private fun setupRecycleView() = with(binding) {
        rvCharacters.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListCharacterBinding =
        FragmentListCharacterBinding.inflate(inflater, container, false)

}