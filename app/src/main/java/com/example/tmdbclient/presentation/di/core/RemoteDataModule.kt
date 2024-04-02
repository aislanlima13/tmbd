package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.BuildConfig
import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.artist.dataresourceimpl.ArtistRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowRemoteDataSource
import com.example.tmdbclient.data.repository.tvshow.dataresoureimpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule() {

    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(
            tmdbService,
            BuildConfig.API_KEY
        )

    @Singleton
    @Provides
    fun providesArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource =
        ArtistRemoteDataSourceImpl(
            tmdbService,
            BuildConfig.API_KEY
        )

    @Singleton
    @Provides
    fun providesTvShowRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(
            tmdbService,
            BuildConfig.API_KEY
        )
}