package com.example.tmdbclient.data.repository.tvshow.dataresoureimpl

import com.example.tmdbclient.data.model.tvshows.TvShow
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl: TvShowCacheDataSource {
    private var tvShowList = ArrayList<TvShow>()

    override suspend fun getTvShowsFromCache(): List<TvShow> =
        tvShowList

    override suspend fun saveTvShowsToCache(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvShows)
    }
}
