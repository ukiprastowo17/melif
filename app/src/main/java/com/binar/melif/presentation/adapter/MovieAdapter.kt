package com.binar.melif.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.binar.melif.BuildConfig
import com.binar.melif.databinding.ItemHeaderTvShowBinding
import com.binar.melif.databinding.ItemSectionTvShowBinding
import com.binar.melif.uimodel.MovieItem
import com.binar.melif.uimodel.TV_SHOW_TYPE_HEADER

class MovieAdapter(var listener: ((MovieItem) -> Unit)? = null)
    : RecyclerView.Adapter<ViewHolder>() {

    val data = mutableListOf<MovieItem>()

    fun setItems(newData: List<MovieItem>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == TV_SHOW_TYPE_HEADER) {
            HomeHeaderItemViewHolder(
                ItemHeaderTvShowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            HomeSectionItemViewHolder(
                ItemSectionTvShowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is HomeHeaderItemViewHolder)
            holder.bind(data[position] as MovieItem.MovieHeaderItem)
        else if (holder is HomeSectionItemViewHolder)
            holder.bind(data[position] as MovieItem.MovieSectionItem)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }
}


class HomeHeaderItemViewHolder(private val binding: ItemHeaderTvShowBinding) :
    ViewHolder(binding.root) {

    fun bind(item: MovieItem.MovieHeaderItem) {
        binding.ivHeaderTvshow.load(BuildConfig.BASE_POSTER_IMG_URL + item.data?.posterPath)
        binding.tvTitleTvshow.text = item.data?.name
    }

}

class HomeSectionItemViewHolder(private val binding: ItemSectionTvShowBinding) :
    ViewHolder(binding.root) {

    private val adapter: MovieListAdapter by lazy {
        MovieListAdapter {
           // AnimeDetailActivity.startActivity(itemView.context, it.animeId)
        }
    }

    fun bind(item: MovieItem.MovieSectionItem) {
        binding.tvTitleSection.text = itemView.context.getText(item.sectionName)
        binding.rvContents.adapter = adapter
        adapter.setItemsMovie(item.data)
    }

}