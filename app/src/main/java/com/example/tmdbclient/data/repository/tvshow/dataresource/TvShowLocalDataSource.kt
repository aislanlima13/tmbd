package com.example.tmdbclient.data.repository.tvshow.dataresource

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshows.TvShow

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDB(): List<TvShow>
    suspend fun saveTvShowsToDB(tvShows: List<TvShow>)
    suspend fun clearAll()
}