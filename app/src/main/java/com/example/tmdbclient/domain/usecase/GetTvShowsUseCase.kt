package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.data.model.tvshows.TvShow
import com.example.tmdbclient.domain.repository.TvShowRepository

class GetTvShowsUseCase(private val repository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = repository.getTvShows()
}