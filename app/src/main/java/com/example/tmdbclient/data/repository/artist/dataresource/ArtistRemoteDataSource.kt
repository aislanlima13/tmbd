package com.example.tmdbclient.data.repository.artist.dataresource

import com.example.tmdbclient.data.model.artists.ArtistsList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistsList>
}