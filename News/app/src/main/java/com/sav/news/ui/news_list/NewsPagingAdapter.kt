package com.sav.news.ui.news_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sav.news.databinding.ItemNewsBinding
import com.sav.news.models.News
import com.sav.news.utils.extensions.loadImage

class NewsPagingAdapter(val onClick: (news: News) -> Unit) :
    PagingDataAdapter<News, NewsPagingAdapter.NewsViewHolder>(object :
        DiffUtil.ItemCallback<News>() {

        override fun areItemsTheSame(
            oldItem: News,
            newItem: News
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: News,
            newItem: News
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    inner class NewsViewHolder(
        private val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.data = news
            binding.imvPhoto.loadImage(news.imageUrl)
            binding.llPost.setOnClickListener {
                onClick(news)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).let { NewsViewHolder(it) }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}