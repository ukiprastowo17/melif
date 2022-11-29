package com.binar.melif.data.network.api.datasource

import com.binar.melif.data.local.dao.MovieDao
import com.binar.melif.data.local.entity.FavoriteMovieEntity


interface MovieFavDataSource {
    suspend fun getAllMovieFav(): List<FavoriteMovieEntity>
    suspend fun insertMovieFav(member: FavoriteMovieEntity): Long


}

class MovieFavDataSourceImpl(private val dao: MovieDao) : MovieFavDataSource {
    override suspend fun getAllMovieFav(): List<FavoriteMovieEntity> {
        return dao.getAllMovies()
    }


    override suspend fun insertMovieFav(movie: FavoriteMovieEntity): Long {
        return dao.insertMovie(movie)
    }




}