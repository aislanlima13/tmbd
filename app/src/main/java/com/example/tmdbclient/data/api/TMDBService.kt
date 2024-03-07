package com.example.tmdbclient.data.api

import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.artists.PersonList
import com.example.tmdbclient.data.model.tvshows.TvShowsList
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Result<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String): Result<TvShowsList>

    @GET("person/popular")
    suspend fun getPopularArtists(@Query("api_key") apiKey: String): Result<PersonList>
}
