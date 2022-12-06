package com.lvb.projects.app_news_mvvm.ui.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lvb.projects.app_news_mvvm.R
import com.lvb.projects.app_news_mvvm.data.local.db.ArticleDatabase
import com.lvb.projects.app_news_mvvm.data.remote.RetrofitInstance
import com.lvb.projects.app_news_mvvm.databinding.FragmentFavoriteBinding
import com.lvb.projects.app_news_mvvm.repository.NewsRepository
import com.lvb.projects.app_news_mvvm.ui.adapter.MainAdapter
import com.lvb.projects.app_news_mvvm.ui.fragments.base.BaseFragment
import com.lvb.projects.app_news_mvvm.util.state.ArticleListEvent
import com.lvb.projects.app_news_mvvm.util.state.ArticleListState

class FavoriteFragment : BaseFragment<FavoriteViewModel, FragmentFavoriteBinding>() {

    private val mainAdapter by lazy { MainAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatch(ArticleListEvent.Fetch)
        setupRecycleView()
        observerResults()
    }

    private fun observerResults() {
        viewModel.favorite.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ArticleListState.Success -> {
                    binding.tvEmptyList.visibility = View.INVISIBLE
                    mainAdapter.differ.submitList(response.list)
                }
                is ArticleListState.ErrorMessage -> {
                    binding.tvEmptyList.visibility = View.INVISIBLE
                    Toast.makeText(
                        requireContext(),
                        "An error happens: ${response.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ArticleListState.Loading -> {
                    binding.tvEmptyList.visibility = View.INVISIBLE
                }

                is ArticleListState.Empty -> {
                    binding.tvEmptyList.visibility = View.VISIBLE
                    mainAdapter.differ.submitList(emptyList())
                }

            }
        }
    }

    private val itemTouchPerCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val article = mainAdapter.differ.currentList[position]
            viewModel.deleteArticle(article)
            Snackbar.make(
                viewHolder.itemView,
                R.string.article_delete_successful,
                Snackbar.LENGTH_SHORT
            ).apply {
                setAction(getString(R.string.undo)) {
                    viewModel.saveArticle(article)
                    mainAdapter.notifyDataSetChanged()
                }
                show()
            }
            observerResults()
        }
    }

    private fun setupRecycleView() {
        with(binding.rvFavorite) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            ItemTouchHelper(itemTouchPerCallback).attachToRecyclerView(this)
        }

        mainAdapter.setOnClickListener { article ->
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToWebViewFragment(article)
            findNavController().navigate(action)
        }
    }


    override fun getViewModel(): Class<FavoriteViewModel> = FavoriteViewModel::class.java

    override fun getFragmentRepository(): NewsRepository = NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))

    override fun getFragmentBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)


}