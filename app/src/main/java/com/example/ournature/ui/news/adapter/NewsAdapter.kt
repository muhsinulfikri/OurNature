package com.example.ournature.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ournature.R
import com.example.ournature.data.api.response.Article
import com.example.ournature.data.local.room.entity.HistoryNews
import com.example.ournature.databinding.FragmentNewsBinding
import com.example.ournature.databinding.ItemNewslistBinding
import com.example.ournature.ui.news.NewsFragment
import com.example.ournature.ui.news.NewsRepository
import com.example.ournature.ui.news.NewsViewModel

class NewsAdapter(private val itemClick: (Article) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var items: MutableList<Article> = mutableListOf()

    fun setItems(items: List<Article>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Article>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            ItemNewslistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindingView(items[position])
//        holder.addNews(items[position], newsViewModel)
    }

    override fun getItemCount(): Int = items.size

    class NewsViewHolder(
        private val binding: ItemNewslistBinding,
        val itemClick: (Article) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindingView(item: Article) {
            with(item) {
                binding.ivNews.setOnClickListener {
                    itemClick(this)
                }
                binding.ivNews.load(item.image) {
                    crossfade(true)
                }
                binding.tvTitleNews.text = item.title
                binding.tvAuthor.text = item.author
            }
        }
    }
}