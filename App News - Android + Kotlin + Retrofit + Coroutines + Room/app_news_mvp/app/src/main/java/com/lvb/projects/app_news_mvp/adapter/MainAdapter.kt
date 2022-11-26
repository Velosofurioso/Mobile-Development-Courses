package com.lvb.projects.app_news_mvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lvb.projects.app_news_mvp.R
import com.lvb.projects.app_news_mvp.model.Article
import kotlinx.android.synthetic.main.item_news.view.*

class MainAdapter: RecyclerView.Adapter<MainAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(rv_article_image)
            text_item_title.text = article.author ?: article.source?.name
            text_item_source.text = article.source?.name ?: article.author
            tv_item_description.text = article.description
            text_item_date_published.text = article.publishedAt

            setOnClickListener {
                onItemCLickListener?.let { click ->
                    click(article)
                }
            }
        }
    }

    private var onItemCLickListener: ((Article) -> Unit) ? = null

    fun setOnClickListener(listener: (Article) -> Unit) {
        onItemCLickListener = listener
    }


}