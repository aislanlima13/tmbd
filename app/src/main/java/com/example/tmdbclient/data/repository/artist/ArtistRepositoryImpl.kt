package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artists.Artist
import com.example.tmdbclient.data.model.artists.ArtistsList
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import retrofit2.Response

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistsLocalDataSource: ArtistLocalDataSource,
    private val artistsCacheDataSource: ArtistCacheDataSource
): ArtistRepository {
    override suspend fun getArtists(): List<Artist> =
        getTvShowsFromCache()

    override suspend fun updateArtists(): List<Artist> {
        val newArtistsList = getArtistsFromAPI()
        artistsLocalDataSource.clearAll()
        artistsLocalDataSource.saveArtistsToDB(newArtistsList)
        artistsCacheDataSource.saveArtistsToCache(newArtistsList)
        return newArtistsList
    }

    suspend fun getArtistsFromAPI(): List<Artist> {
        lateinit var artistsList: List<Artist>

        try {
            val response: Response<ArtistsList> = artistRemoteDataSource.getArtists()
            val body = response.body()

            if (body != null) {
                artistsList = body.artists
            }
        } catch (exception: Exception) {
            Log.e("tmdb_exception", exception.message.toString())
        }

        return artistsList
    }

    suspend fun getArtistsFromDB(): List<Artist> {
        lateinit var artistsList: List<Artist>

        try {
            artistsList = artistsLocalDataSource.getArtistsFromDB()
        } catch (exception: Exception) {
            Log.e("tmdb_exception", exception.message.toString())
        }

        if (artistsList.isNotEmpty()) {
            return artistsList
        } else {
            artistsList = getArtistsFromAPI()
            artistsLocalDataSource.saveArtistsToDB(artistsList)
        }

        return artistsList
    }

    suspend fun getTvShowsFromCache(): List<Artist> {
        lateinit var artistsList: List<Artist>

        try {
            artistsList = artistsCacheDataSource.getArtistsFromCache()
        } catch (exception: Exception) {
            Log.e("tmdb_exception", exception.message.toString())
        }

        if (artistsList.isNotEmpty()) {
            return artistsList
        } else {
            artistsList = getArtistsFromDB()
            artistsCacheDataSource.saveArtistsToCache(artistsList)
        }

        return artistsList
    }
}