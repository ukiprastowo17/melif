package com.binar.melif.data.network.api.datasource

import com.binar.melif.BuildConfig
import com.binar.melif.data.network.model.MelifModel
import com.binar.melif.data.network.service.TvShowApiService

interface TvShowApiDataSource {
    suspend fun getTopRatedTv(page:Int = 1) : MelifModel

    suspend fun getPopularTv(page:Int = 1) : MelifModel

    suspend fun getAiringTv() :MelifModel
}

class TvShowDataSourceImpl(private val service: TvShowApiService): TvShowApiDataSource{
    override suspend fun getTopRatedTv(page: Int): MelifModel {
        return service.getTopRatedTv(BuildConfig.API_KEY, page)
    }

    override suspend fun getPopularTv(page: Int): MelifModel {
        return service.getPopularTv(BuildConfig.API_KEY, page)
    }

    override suspend fun getAiringTv(): MelifModel{
        return service.getAiringTv(BuildConfig.API_KEY,1)
    }

}