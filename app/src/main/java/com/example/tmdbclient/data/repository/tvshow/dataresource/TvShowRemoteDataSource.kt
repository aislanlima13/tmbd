package com.example.tmdbclient.data.repository.tvshow.dataresource

import com.example.tmdbclient.data.model.tvshows.TvShowsList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowsList>
}