package com.example.tmdbclient.data.repository.tvshow.dataresource

import com.example.tmdbclient.data.model.tvshows.TvShow

interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache(): List<TvShow>
    suspend fun saveTvShowsToCache(tvShows: List<TvShow>)
}