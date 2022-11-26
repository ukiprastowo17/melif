package com.binar.melif.data.network.api.datasource

import com.binar.melif.BuildConfig
import com.binar.melif.data.network.api.model.MovieDetail
import com.binar.melif.data.network.api.model.TvShowDetail
import com.binar.melif.data.network.api.service.MelifApiService

interface MelifApiDataSource {
    suspend fun getDetailTvShow(tv_id: String): TvShowDetail
    suspend fun getDetailMovie(movie_id: String): MovieDetail
}

class MelifApiDataSourceImpl(private val service: MelifApiService) : MelifApiDataSource {
    override suspend fun getDetailTvShow(tv_id: String): TvShowDetail {
        return service.getTvShowDetail(tv_id, BuildConfig.API_KEY)
    }

    override suspend fun getDetailMovie(movie_id: String): MovieDetail {
        return service.getMovieDetail(movie_id, BuildConfig.API_KEY)
    }
}