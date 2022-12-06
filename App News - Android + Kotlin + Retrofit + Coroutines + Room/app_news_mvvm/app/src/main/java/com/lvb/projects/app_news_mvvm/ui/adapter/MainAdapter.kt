package com.lvb.projects.app_news_mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lvb.projects.app_news_mvvm.databinding.ItemNewsBinding
import com.lvb.projects.app_news_mvvm.data.local.model.Article

class MainAdapter: RecyclerView.Adapter<MainAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        with(holder) {
            with(differ.currentList[position]) {
                Glide.with(holder.itemView.context).load(urlToImage).into(binding.rvArticleImage)
                binding.textItemTitle.text = author ?: source?.name
                binding.textItemSource.text = source?.name ?: author
                binding.tvItemDescription.text = description
                binding.textItemDatePublished.text = publishedAt

                holder.itemView.setOnClickListener{
                    onItemCLickListener?.let { click ->
                        click(this)
                    }
                }
            }
        }
    }

    private var onItemCLickListener: ((Article) -> Unit) ? = null

    fun setOnClickListener(listener: (Article) -> Unit) {
        onItemCLickListener = listener
    }


}