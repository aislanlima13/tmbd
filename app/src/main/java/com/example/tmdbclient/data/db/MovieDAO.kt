package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.movie.Movie

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM tmdb_popular_movies")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM tmdb_popular_movies")
    suspend fun getMovies(): List<Movie>
}
