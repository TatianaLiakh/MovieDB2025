package com.example.moviedb2025.database

import com.example.moviedb2025.models.Movie
import com.example.moviedb2025.models.MovieResponse
import com.example.moviedb2025.network.MovieDBApiService

interface MoviesRepository {
    suspend fun getPopularMovies(): MovieResponse
    suspend fun getTopRatedMovies(): MovieResponse
}

class NetworkMoviesRepository(private val apiService: MovieDBApiService) : MoviesRepository {
    override suspend fun getPopularMovies(): MovieResponse {
        return apiService.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): MovieResponse {
        return apiService.getTopRatedMovies()
    }
}

interface SavedMoviesRepository{
    suspend fun getSavedMovies(): List<Movie>
    suspend fun insertMovie(movie: Movie)
    suspend fun getMovie(id: Long): Movie
    suspend fun deleteMovie(movie: Movie)
}

class FavoriteMoviesRepository(private val movieDAO: MovieDataAcсessObj):SavedMoviesRepository{
    override suspend fun getSavedMovies(): List<Movie> {
        return movieDAO.getSavedMovies()
    }

    override suspend fun insertMovie(movie: Movie) {
        movieDAO.insertMovie(movie)
    }

    override suspend fun getMovie(id: Long): Movie {
        return movieDAO.getMovie(id)
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDAO.deleteMovie(movie.id)
    }

}