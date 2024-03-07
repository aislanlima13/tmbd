package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvshows.TvShow

@Dao
interface TvShowDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvShows: List<TvShow>)

    @Query("DELETE FROM tmdb_popular_tv_show")
    suspend fun deleteAllTvShows()

    @Query("SELECT * FROM tmdb_popular_tv_show")
    suspend fun getTvShows(): List<TvShow>
}
