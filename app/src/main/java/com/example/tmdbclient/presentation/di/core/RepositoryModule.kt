package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository =
        MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )

    @Singleton
    @Provides
    fun providesArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource,
    ): ArtistRepository =
        ArtistRepositoryImpl(
            artistRemoteDataSource,
            artistLocalDataSource,
            artistCacheDataSource
        )

    @Singleton
    @Provides
    fun providesTvShowRepository(
        tvRemoteDataSource: TvShowRemoteDataSource,
        tvLocalDataSource: TvShowLocalDataSource,
        tvCacheDataSource: TvShowCacheDataSource,
    ): TvShowRepository =
        TvShowRepositoryImpl(
            tvRemoteDataSource,
            tvLocalDataSource,
            tvCacheDataSource
        )
}
