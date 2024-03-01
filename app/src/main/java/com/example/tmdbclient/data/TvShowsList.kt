package com.example.tmdbclient.data


import com.google.gson.annotations.SerializedName

data class TvShowsList(
    @SerializedName("results")
    val tvShows: List<TvShow>
)