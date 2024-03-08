package com.example.tmdbclient.data.repository.tvshow.dataresoureimpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.tvshows.TvShowsList
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String): TvShowRemoteDataSource {

    override suspend fun getTvShows(): Response<TvShowsList> =
        tmdbService.getPopularTvShows(apiKey)
}
