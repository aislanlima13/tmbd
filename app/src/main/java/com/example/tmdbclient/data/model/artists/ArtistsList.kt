package com.example.tmdbclient.data.model.artists


import com.google.gson.annotations.SerializedName

data class ArtistsList(
    @SerializedName("results")
    val artists: List<Artist>
)
