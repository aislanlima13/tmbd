package com.example.tmdbclient.data.repository.artist.dataresourceimpl

import com.example.tmdbclient.data.db.ArtistsDAO
import com.example.tmdbclient.data.model.artists.Artist
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistsLocalDataSourceImpl(private val dao: ArtistsDAO): ArtistLocalDataSource {
    override suspend fun getArtistsFromDB(): List<Artist> =
        dao.getArtists()

    override suspend fun saveArtistsToDB(artists: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.saveArtists(artists)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAllArtists()
        }
    }
}