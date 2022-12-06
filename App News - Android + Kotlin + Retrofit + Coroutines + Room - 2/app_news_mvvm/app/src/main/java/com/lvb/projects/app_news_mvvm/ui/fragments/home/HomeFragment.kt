package com.lvb.projects.app_news_mvvm.ui.fragments.home

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
import com.lvb.projects.app_news_mvvm.databinding.FragmentHomeBinding
import com.lvb.projects.app_news_mvvm.repository.NewsRepository
import com.lvb.projects.app_news_mvvm.ui.adapter.MainAdapter
import com.lvb.projects.app_news_mvvm.ui.fragments.base.BaseFragment
import com.lvb.projects.app_news_mvvm.util.hide
import com.lvb.projects.app_news_mvvm.util.show
import com.lvb.projects.app_news_mvvm.util.state.StateResource
import com.lvb.projects.app_news_mvvm.util.toast

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()
        observerResults()
    }

    private fun observerResults() {
        viewModel.getAll.observe(viewLifecycleOwner) { response ->
            when (response) {
                is StateResource.Success -> {
                    binding.rvProgressBar.visibility = View.INVISIBLE
                    response.data?.let { data ->
                        mainAdapter.differ.submitList(data.articles.toList())
                    }
                }
                is StateResource.Error -> {
                    binding.rvProgressBar.hide()
                    toast(

                        "An error happens: ${response.message.toString()}",
                        Toast.LENGTH_SHORT
                    )
                }
                is StateResource.Loading -> {
                    binding.rvProgressBar.show()
                }

            }
        }
    }

    private fun setupRecycleView() = with(binding) {
        rvNews.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }

        mainAdapter.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToWebViewFragment(it)

            findNavController().navigate(action)
        }
    }


    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getFragmentRepository(): NewsRepository =
        NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))

    override fun getFragmentBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
}