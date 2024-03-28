package com.example.tmdbclient.data.repository.artist.dataresourceimpl

import com.example.tmdbclient.data.model.artists.Artist
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistCacheDataSource

class ArtistCacheDataSourceImpl: ArtistCacheDataSource {
    private var artistsList = ArrayList<Artist>()
    override suspend fun getArtistsFromCache(): List<Artist> =
        artistsList

    override suspend fun saveArtistsToCache(artists: List<Artist>) {
        artistsList.clear()
        artistsList = ArrayList(artists)
    }
}