package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.data.model.artists.Person
import com.example.tmdbclient.domain.repository.ArtistRepository

class GetArtistsUseCase(private val repository: ArtistRepository) {
    suspend fun execute(): List<Person>? = repository.getArtists()
}