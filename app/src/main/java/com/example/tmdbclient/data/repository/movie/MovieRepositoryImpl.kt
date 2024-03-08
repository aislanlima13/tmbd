package com.example.tmdbclient.data.repository.movie

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.MovieRepository
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
): MovieRepository {
    override suspend fun getMovies(): List<Movie>? =
        getMoviesFromCache()

    override suspend fun updateMovies(): List<Movie>? {
        val newMovieList = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newMovieList)
        movieCacheDataSource.saveMoviesToCache(newMovieList)
        return newMovieList
    }

    suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            val response: Response<MovieList> = movieRemoteDataSource.getMovies()
            val body = response.body()

            if (body != null) {
                movieList = body.movies
            }
        } catch (exception: Exception) {
            Log.e("tmdb_exception", exception.message.toString())
        }

        return movieList
    }

    suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        } catch (exception: Exception) {
            Log.e("tmdb_exception", exception.message.toString())
        }

        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }

        return movieList
    }

    suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (exception: Exception) {
            Log.e("tmdb_exception", exception.message.toString())
        }

        if (movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
        }

        return movieList
    }
}