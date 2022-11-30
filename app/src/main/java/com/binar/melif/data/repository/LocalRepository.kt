package com.binar.melif.data.repository

import com.binar.melif.base.BaseRepository
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.local.entity.FavoriteMovieEntity
import com.binar.melif.data.network.api.datasource.FavDataSource


interface LocalRepository {
    suspend fun getAllMovieFav(): Resource<List<FavoriteMovieEntity>>
    suspend fun insertMovieFav(movieFav: FavoriteMovieEntity): Resource<Number>


}

class LocalRepositoryImpl(
    private val movieFavDataSource: FavDataSource

    ) : BaseRepository(), LocalRepository {
    override suspend fun getAllMovieFav(): Resource<List<FavoriteMovieEntity>> {
        return proceed { movieFavDataSource.getAllMovieFav() }
    }


    override suspend fun insertMovieFav(movieFav: FavoriteMovieEntity): Resource<Number> {
        return proceed { movieFavDataSource.insertMovieFav(movieFav) }
    }





}