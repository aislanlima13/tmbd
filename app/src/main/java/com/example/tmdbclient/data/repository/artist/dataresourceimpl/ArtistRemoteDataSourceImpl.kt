package com.example.tmdbclient.data.repository.artist.dataresourceimpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.artists.ArtistsList
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
): ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<ArtistsList> =
        tmdbService.getPopularArtists(apiKey)
}