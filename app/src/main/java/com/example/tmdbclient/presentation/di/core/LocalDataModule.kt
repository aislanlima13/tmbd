package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.db.ArtistsDAO
import com.example.tmdbclient.data.db.MovieDAO
import com.example.tmdbclient.data.db.TvShowDAO
import com.example.tmdbclient.data.repository.artist.dataresource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.dataresourceimpl.ArtistsLocalDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasourceimpl.MovieLocalDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.dataresoureimpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun providesMovieLocalDataSource(movieDAO: MovieDAO): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDAO)

    @Singleton
    @Provides
    fun providesArtistLocalDataSource(artistsDAO: ArtistsDAO): ArtistLocalDataSource =
        ArtistsLocalDataSourceImpl(artistsDAO)

    @Singleton
    @Provides
    fun providesTvShowLocalDataSource(tvShowDAO: TvShowDAO): TvShowLocalDataSource =
        TvShowLocalDataSourceImpl(tvShowDAO)
}