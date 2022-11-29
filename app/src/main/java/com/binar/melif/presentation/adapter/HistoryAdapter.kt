package com.binar.melif.presentation.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.melif.data.local.entity.FavoriteMovieEntity
import com.binar.melif.databinding.ItemMovieFavBinding


class HistoryAdapter(private val itemClick: (FavoriteMovieEntity) -> Unit) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var playersArrList: ArrayList<String>? = null
    private var items: MutableList<FavoriteMovieEntity> = mutableListOf()

    fun setItems(items: List<FavoriteMovieEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemMovieFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    class HistoryViewHolder(private val binding: ItemMovieFavBinding, val itemClick: (FavoriteMovieEntity) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: FavoriteMovieEntity) {
            binding.tvIdMember.text = item.title
//            binding.tvNameMember.text = item.group_name_result

            with(item) {
                itemView.setOnClickListener { itemClick(this) }
            }

        }
    }

}