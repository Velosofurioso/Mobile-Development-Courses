package com.lvb.projects.app_marvel.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lvb.projects.app_marvel.R
import com.lvb.projects.app_marvel.databinding.FragmentFavoriteCharacterBinding
import com.lvb.projects.app_marvel.ui.adapters.CharacterAdapter
import com.lvb.projects.app_marvel.ui.base.BaseFragment
import com.lvb.projects.app_marvel.ui.state.ResourceState
import com.lvb.projects.app_marvel.util.hide
import com.lvb.projects.app_marvel.util.show
import com.lvb.projects.app_marvel.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteCharacterFragment :
    BaseFragment<FragmentFavoriteCharacterBinding, FavoriteCharacterViewModel>() {
    override val viewModel: FavoriteCharacterViewModel by viewModels()
    private val characterAdapter by lazy { CharacterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        clickAdapter()
        collectObserver()
    }

    private fun collectObserver() = lifecycleScope.launch {
        viewModel.favorites.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { values ->
                        binding.tvEmptyList.hide()
                        characterAdapter.characters = values.toList()
                    }
                }
                is ResourceState.Empty -> {
                    binding.tvEmptyList.show()
                }
                else -> {}
            }

        }
    }

    private fun setupRecycleView() = with(binding) {
        rvFavoriteCharacter.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(context)
        }
        ItemTouchHelper(itemTouchPerCallback()).attachToRecyclerView(rvFavoriteCharacter)
    }

    private fun clickAdapter() {
        characterAdapter.setOnCLickListener { characterModel ->
            val action =
                FavoriteCharacterFragmentDirections.actionFavoriteCharacterFragmentToDetailsCharacterFragment(
                    characterModel
                )
            findNavController().navigate(action)
        }
    }

    private fun itemTouchPerCallback(): ItemTouchHelper.SimpleCallback {
        return object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val character = characterAdapter.getCharacterPosition(viewHolder.adapterPosition)
                viewModel.delete(character).also {
                    toast(getString(R.string.message_delete_character))
                }
            }

        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteCharacterBinding =
        FragmentFavoriteCharacterBinding.inflate(inflater, container, false)

}