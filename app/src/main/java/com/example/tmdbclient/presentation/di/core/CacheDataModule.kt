package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.dataresource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.dataresourceimpl.ArtistCacheDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasourceimpl.MovieCacheDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.dataresource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.dataresoureimpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideMovieCacheDataSource(): MovieCacheDataSource =
        MovieCacheDataSourceImpl()

    @Singleton
    @Provides
    fun provideArtistsCacheDataSource(): ArtistCacheDataSource =
        ArtistCacheDataSourceImpl()

    @Singleton
    @Provides
    fun provideTvShowCacheDataSource(): TvShowCacheDataSource =
        TvShowCacheDataSourceImpl()
}