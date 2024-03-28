package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.artists.Artist

interface ArtistRepository {
    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists(): List<Artist>?
}
