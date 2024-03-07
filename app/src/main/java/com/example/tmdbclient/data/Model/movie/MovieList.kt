package com.example.tmdbclient.data.Model.movie


import com.example.tmdbclient.data.Model.movie.Movie
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    val movies: List<Movie>
)
