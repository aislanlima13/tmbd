package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.tvshows.TvShow
import com.example.tmdbclient.data.model.tvshows.TvShowsList
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.TvShowRepository
import retrofit2.Response

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
): TvShowRepository {
    override suspend fun getTvShows(): List<TvShow> =
        getTvShowsFromCache()

    override suspend fun updateTvShows(): List<TvShow> {
        val newTvShowList = getTvShowsFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newTvShowList)
        tvShowCacheDataSource.saveTvShowsToCache(newTvShowList)
        return newTvShowList
    }

    suspend fun getTvShowsFromAPI(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>

        try {
            val response: Response<TvShowsList> = tvShowRemoteDataSource.getTvShows()
            val body = response.body()

            if (body != null) {
                tvShowList = body.tvShows
            }
        } catch (exception: Exception) {
            Log.e("tmdb_exception", exception.message.toString())
        }

        return tvShowList
    }

    suspend fun getTvShowsFromDB(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (exception: Exception) {
            Log.e("tmdb_exception", exception.message.toString())
        }

        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
        }

        return tvShowList
    }

    suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>

        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (exception: Exception) {
            Log.e("tmdb_exception", exception.message.toString())
        }

        if (tvShowList.isNotEmpty()) {
            return tvShowList
        } else {
            tvShowList = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
        }

        return tvShowList
    }
}