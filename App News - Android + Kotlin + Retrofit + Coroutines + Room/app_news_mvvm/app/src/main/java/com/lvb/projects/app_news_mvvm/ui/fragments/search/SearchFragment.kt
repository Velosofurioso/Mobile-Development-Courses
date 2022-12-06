package com.lvb.projects.app_news_mvvm.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvb.projects.app_news_mvvm.data.local.db.ArticleDatabase
import com.lvb.projects.app_news_mvvm.data.remote.RetrofitInstance
import com.lvb.projects.app_news_mvvm.databinding.FragmentSearchBinding
import com.lvb.projects.app_news_mvvm.repository.NewsRepository
import com.lvb.projects.app_news_mvvm.ui.adapter.MainAdapter
import com.lvb.projects.app_news_mvvm.ui.fragments.base.BaseFragment
import com.lvb.projects.app_news_mvvm.util.state.StateResource
import com.lvb.projects.app_news_mvvm.util.UtilQueryTextListener

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    private val  mainAdapter by lazy { MainAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        search()
        observerResults()
    }

    private fun setupRecycleView() = with(binding) {
        rvSearch.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }

        mainAdapter.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToWebViewFragment(it)

            findNavController().navigate(action)
        }
    }

    private fun search() {
        binding.searchNews.setOnQueryTextListener(
            UtilQueryTextListener(this.lifecycle) { newsText ->
                newsText?.let { query ->
                    if (query.isNotEmpty()) {
                        viewModel.fetchSearch(query)
                        binding.searchProgressBar.visibility = View.VISIBLE
                    }
                }

            }
        )
    }

    private fun observerResults() {
        viewModel.search.observe(viewLifecycleOwner) { response ->
            when (response) {
                is StateResource.Success -> {
                    binding.searchProgressBar.visibility = View.INVISIBLE
                    response.data?.let { data ->
                        mainAdapter.differ.submitList(data.articles.toList())
                    }
                }
                is StateResource.Error -> {
                    binding.searchProgressBar.visibility = View.INVISIBLE
                    Toast.makeText(
                        requireContext(),
                        "An error happens: ${response.message.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is StateResource.Loading -> {
                    binding.searchProgressBar.visibility = View.VISIBLE
                }

            }
        }
    }

    override fun getViewModel(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun getFragmentRepository(): NewsRepository = NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke((requireContext())))

    override fun getFragmentBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
}