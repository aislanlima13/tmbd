package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.presentation.di.movie.MovieScope
import com.example.tmdbclient.presentation.ui.tvshow.TvShowFragment
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {

    fun inject(tvShowFragment: TvShowFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): TvShowSubComponent
    }
}