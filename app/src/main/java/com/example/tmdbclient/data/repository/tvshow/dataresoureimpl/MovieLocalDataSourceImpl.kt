package com.example.tmdbclient.data.repository.tvshow.dataresoureimpl

import com.example.tmdbclient.data.db.MovieDAO
import com.example.tmdbclient.data.db.TvShowDAO
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshows.TvShow
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val tvShowDao: TvShowDAO): TvShowLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> =
        tvShowDao.getTvShows()

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShows(tvShows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }
}