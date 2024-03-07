package com.example.tmdbclient.data.Model.tvshows


import com.example.tmdbclient.data.Model.tvshows.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowsList(
    @SerializedName("results")
    val tvShows: List<TvShow>
)