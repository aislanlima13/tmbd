package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import com.example.tmdbclient.domain.usecase.GetArtistsUseCase
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase =
        GetMoviesUseCase(movieRepository)

    @Singleton
    @Provides
    fun providesUpdateMovieUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase =
        UpdateMoviesUseCase(movieRepository)

    @Singleton
    @Provides
    fun providesGetArtistUseCase(artistRepository: ArtistRepository): GetArtistsUseCase =
        GetArtistsUseCase(artistRepository)

    @Singleton
    @Provides
    fun providesUpdateArtistUseCase(artistRepository: ArtistRepository): UpdateArtistsUseCase =
        UpdateArtistsUseCase(artistRepository)

    @Singleton
    @Provides
    fun providesGetTvShowUseCase(tvShowRepository: TvShowRepository): GetTvShowsUseCase =
        GetTvShowsUseCase(tvShowRepository)

    @Singleton
    @Provides
    fun providesUpdateTvShowUseCase(tvShowRepository: TvShowRepository): UpdateTvShowsUseCase =
        UpdateTvShowsUseCase(tvShowRepository)
}