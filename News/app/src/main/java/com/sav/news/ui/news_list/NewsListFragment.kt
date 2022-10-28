package com.sav.news.ui.news_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.sav.news.base.BaseFragment
import com.sav.news.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : BaseFragment() {

    private lateinit var binding: FragmentNewsListBinding
    private val viewModel: NewsListViewModel by viewModels()
    private val adapter: NewsPagingAdapter by lazy {
        NewsPagingAdapter {
            navController.navigate(
                NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(
                    it
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObserver()
        viewModel.getLstNews(binding.tfSearch.text ?: "")

        binding.swipeLayout.setOnRefreshListener {
            viewModel.getLstNews((binding.tfSearch.text ?: ""))
        }

        binding.rcvNewsList.adapter = adapter

        binding.main.setOnClickListener {
            unFocusEditText()
        }

        binding.tfSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                unFocusEditText()
            }
            true
        }
    }

    private fun unFocusEditText() {
        hideKeyboard()
        binding.tfSearch.clearFocus()
    }

    private fun registerObserver() {
        viewModel.newsList.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
            binding.swipeLayout.isRefreshing = false
        }
    }

}