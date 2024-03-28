package com.example.tmdbclient.data.repository.artist.dataresource

import com.example.tmdbclient.data.model.artists.Artist

interface ArtistCacheDataSource {
    suspend fun getArtistsFromCache(): List<Artist>
    suspend fun saveArtistsToCache(artists: List<Artist>)
}
