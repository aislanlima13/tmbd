package com.example.tmdbclient.data.repository.artist.dataresource

import com.example.tmdbclient.data.model.artists.Artist

interface ArtistLocalDataSource {
    suspend fun getArtistsFromDB(): List<Artist>
    suspend fun saveArtistsToDB(artists: List<Artist>)
    suspend fun clearAll()
}