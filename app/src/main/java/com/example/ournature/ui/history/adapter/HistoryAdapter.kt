package com.example.ournature.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ournature.data.local.room.entity.HistoryNews
import com.example.ournature.databinding.ItemHistoryListBinding
import com.example.ournature.databinding.ItemNewslistBinding

class HistoryAdapter(private val itemClick: (HistoryNews) -> Unit) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var items : MutableList<HistoryNews> = mutableListOf()

    fun setItems(items: List<HistoryNews>){
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<HistoryNews>){
        this.items.addAll(items)
    }

    fun clearItems(){
        this.items.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindingView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class HistoryViewHolder(
        private val binding: ItemHistoryListBinding, val itemClick: (HistoryNews) -> Unit): RecyclerView.ViewHolder(binding.root){

        fun bindingView(item: HistoryNews){
            with(item){
                itemView.setOnClickListener { itemClick(this) }
                binding.tvTitle.text = item.title

            }
        }
    }
}