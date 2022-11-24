package com.binar.melif.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.binar.melif.data.network.model.TvShowResult
import com.binar.melif.databinding.ItemTvShowPosterBinding
import com.binar.melif.databinding.ItemTvShowPosterGridBinding

interface PosterViewHolder {
    fun bindView(item: TvShowResult?)
}

class PosterViewHolderImpl(
    private val binding: ItemTvShowPosterBinding,
    val itemClick: (TvShowResult) -> Unit
) : RecyclerView.ViewHolder(binding.root), PosterViewHolder {

    override fun bindView(item: TvShowResult?) {
        item?.let { tvShow ->
            binding.ivPoster.load(tvShow.posterPath)
            itemView.setOnClickListener { itemClick(tvShow) }
        }
    }
}

class GridPosterViewHolderImpl(
    private val binding: ItemTvShowPosterGridBinding,
    val itemClick: (TvShowResult) -> Unit
) : RecyclerView.ViewHolder(binding.root), PosterViewHolder {

    override fun bindView(item: TvShowResult?) {
        item?.let { tvShow ->
            binding.ivPoster.load(tvShow.posterPath)
            itemView.setOnClickListener { itemClick(tvShow) }
        }
    }
}
