package com.binar.melif.data.repository

import com.binar.melif.base.BaseRepository
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.network.api.datasource.TvShowApiDataSource
import com.binar.melif.data.network.model.MelifModel

interface TvShowRepository {
    suspend fun getTopRatedTvList(page:Int = 1) : Resource<MelifModel>

    suspend fun getPopularTvList(page:Int = 1) : Resource<MelifModel>

    suspend fun getAiringTvList() : Resource<MelifModel>
}

class TvShowRepositoryImpl(private val networkDataSource: TvShowApiDataSource): TvShowRepository,BaseRepository(){
    override suspend fun getTopRatedTvList(page: Int): Resource<MelifModel> {
        return doNetworkCall { networkDataSource.getTopRatedTv(page)  }
    }

    override suspend fun getPopularTvList(page: Int): Resource<MelifModel> {
        return doNetworkCall { networkDataSource.getPopularTv(page)  }
    }

    override suspend fun getAiringTvList(): Resource<MelifModel> {
        return doNetworkCall { networkDataSource.getAiringTv()  }
    }
}