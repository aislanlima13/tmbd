package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.artists.Artist

@Dao
interface ArtistsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists: List<Artist>)

    @Query("DELETE FROM tmdb_popular_artists")
    suspend fun deleteAllArtists()

    @Query("SELECT * FROM tmdb_popular_artists")
    suspend fun getArtists(): List<Artist>
}
