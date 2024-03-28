package com.example.tmdbclient.presentation.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.tvshows.TvShow
import com.example.tmdbclient.databinding.ListItemBinding

class TvShowAdapter(): RecyclerView.Adapter<TvShowViewHolder>() {
    private val tvShowList = ArrayList<TvShow>()

    fun setList(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )

        return TvShowViewHolder(binding)
    }

    override fun getItemCount(): Int = tvShowList.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShowList[position])
    }
}

class TvShowViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(tvShow: TvShow) {
        binding.tvTitle.text = tvShow.name
        binding.tvDescription.text = tvShow.overview

        val posterUrl = "https://image.tmdb.org/t/p/w500${tvShow.posterPath}"
        Glide.with(binding.ivHeader.context)
            .load(posterUrl)
            .into(binding.ivHeader)
    }
}