package com.binar.melif.data.repository

import com.binar.melif.base.BaseRepository
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.network.api.datasource.MelifApiDataSource
import com.binar.melif.data.network.api.model.*

interface Repository {
    suspend fun getDetailTvShow(tv_id: String): Resource<TvShowDetail>
    suspend fun getVideoTvShow(tv_id: String): Resource<MelifVideo>
    suspend fun getDetailMovie(movie_id: String): Resource<MovieDetail>
    suspend fun getVideoMovie(movie_id: String): Resource<MelifVideo>
}

class RepositoryImpl(private val networkDataSource: MelifApiDataSource) : Repository,
    BaseRepository() {
    override suspend fun getDetailTvShow(tv_id: String): Resource<TvShowDetail> {
        return doNetworkCall { networkDataSource.getDetailTvShow(tv_id) }
    }

    override suspend fun getVideoTvShow(tv_id: String): Resource<MelifVideo> {
        return doNetworkCall { networkDataSource.getVideoTvShow(tv_id) }
    }

    override suspend fun getDetailMovie(movie_id: String): Resource<MovieDetail> {
        return doNetworkCall { networkDataSource.getDetailMovie(movie_id) }
    }

    override suspend fun getVideoMovie(movie_id: String): Resource<MelifVideo> {
        return doNetworkCall { networkDataSource.getVideoMovie(movie_id) }
    }
}