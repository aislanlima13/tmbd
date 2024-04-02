package com.example.tmdbclient.presentation.di.core

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.tmdbclient.data.db.ArtistsDAO
import com.example.tmdbclient.data.db.MovieDAO
import com.example.tmdbclient.data.db.TMDBDatabase
import com.example.tmdbclient.data.db.TvShowDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideMovieDataBase(app: Application): TMDBDatabase =
        Room.databaseBuilder(app, TMDBDatabase::class.java, "tmdbcliente")
            .build()

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDAO =
        tmdbDatabase.movieDao()

    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase): ArtistsDAO =
        tmdbDatabase.artistDao()

    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase): TvShowDAO =
        tmdbDatabase.tvDao()
}