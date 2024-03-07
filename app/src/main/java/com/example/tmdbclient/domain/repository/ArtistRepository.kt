package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.artists.Person

interface ArtistRepository {
    suspend fun getArtists(): List<Person>?
    suspend fun updateArtists(): List<Person>?
}
