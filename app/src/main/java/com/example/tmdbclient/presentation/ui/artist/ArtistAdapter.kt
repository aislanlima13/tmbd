package com.example.tmdbclient.presentation.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.artists.Artist
import com.example.tmdbclient.databinding.ListItemBinding

class ArtistAdapter: RecyclerView.Adapter<ArtistViewHolder>() {
    private val artistList = ArrayList<Artist>()

    fun setList(artists: List<Artist>) {
        artistList.clear()
        artistList.addAll(artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )

        return ArtistViewHolder(binding)
    }

    override fun getItemCount(): Int = artistList.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(artistList[position])
    }
}

class ArtistViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(artist: Artist) {
        binding.tvTitle.text = artist.name
        binding.tvDescription.text = artist.popularity.toString()

        val posterUrl = "https://image.tmdb.org/t/p/w500${artist.profilePath}"
        Glide.with(binding.ivHeader.context)
            .load(posterUrl)
            .into(binding.ivHeader)
    }
}