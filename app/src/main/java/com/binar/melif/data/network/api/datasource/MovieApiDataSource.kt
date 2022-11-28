package com.binar.melif.data.network.api.datasource

import com.binar.melif.BuildConfig
import com.binar.melif.data.network.model.MovieModel
import com.binar.melif.data.network.service.MovieApiService

interface MovieApiDataSource     {
    suspend fun getNowPlayingMovie(page:Int = 1) : MovieModel

    suspend fun getPopularMovie(page:Int = 1) : MovieModel

    suspend fun getTopRatedMovie(page:Int = 1) :MovieModel
}

class MovieDataSourceImpl(private val service: MovieApiService): MovieApiDataSource{
    override suspend fun getNowPlayingMovie(page: Int): MovieModel {
        return service.getNowPlayingMovie(BuildConfig.API_KEY, page)
    }

    override suspend fun getPopularMovie(page: Int): MovieModel {
        return service.getPopularMovie(BuildConfig.API_KEY, page)
    }

    override suspend fun getTopRatedMovie(page: Int): MovieModel {
        return service.getTopRatedMovie(BuildConfig.API_KEY, page)
    }


}