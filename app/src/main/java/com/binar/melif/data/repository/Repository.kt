package com.binar.melif.data.repository

import com.binar.melif.base.BaseRepository
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.local.entity.FavoriteMovieEntity
import com.binar.melif.data.network.api.datasource.MelifApiDataSource
import com.binar.melif.data.network.api.datasource.MovieFavDataSource
import com.binar.melif.data.network.api.model.MovieDetail
import com.binar.melif.data.network.api.model.TvShowDetail

interface Repository {
    suspend fun getDetailTvShow(tv_id: String): Resource<TvShowDetail>
    suspend fun getDetailMovie(movie_id: String): Resource<MovieDetail>
    suspend fun getAllMovieFav(): Resource<List<FavoriteMovieEntity>>
    suspend fun insertMovieFav(movieFav: FavoriteMovieEntity): Resource<Number>
}

class RepositoryImpl(private val networkDataSource: MelifApiDataSource,
                     private val movieFavDataSource: MovieFavDataSource
) : Repository,
    BaseRepository() {
    override suspend fun getDetailTvShow(tv_id: String): Resource<TvShowDetail> {
        return doNetworkCall { networkDataSource.getDetailTvShow(tv_id) }
    }

    override suspend fun getDetailMovie(movie_id: String): Resource<MovieDetail> {
        return doNetworkCall { networkDataSource.getDetailMovie(movie_id) }
    }

    override suspend fun getAllMovieFav(): Resource<List<FavoriteMovieEntity>> {
        return proceed { movieFavDataSource.getAllMovieFav() }
    }


    override suspend fun insertMovieFav(movieFav: FavoriteMovieEntity): Resource<Number> {
        return proceed { movieFavDataSource.insertMovieFav(movieFav) }
    }
}