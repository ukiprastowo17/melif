package com.binar.melif.data.network.api.datasource

import com.binar.melif.data.network.model.TvShowResult
import com.binar.melif.data.network.service.TvShowApiService

interface TvShowApiDataSource {
    suspend fun getTopRatedTv(api_key:String = "d5524f7d769b5bb3c30b5abbf9706aa3",
                              language: String = "en-Us",
                              page:Int = 1) : List<TvShowResult>

    suspend fun getPopularTv(api_key:String = "d5524f7d769b5bb3c30b5abbf9706aa3",
                              language: String = "en-Us",
                              page:Int = 1) : List<TvShowResult>

    suspend fun getLatestTv(api_key:String = "d5524f7d769b5bb3c30b5abbf9706aa3",
                              language: String = "en-Us") : List<TvShowResult>
}

class TvShowDataSourceImpl(private val service: TvShowApiService): TvShowApiDataSource{
    override suspend fun getTopRatedTv(api_key: String, language: String, page: Int): List<TvShowResult> {
        return service.getTopRatedTv(api_key,language,page)
    }

    override suspend fun getPopularTv(api_key: String, language: String, page: Int): List<TvShowResult> {
        return service.getPopulerTv(api_key,language,page)
    }

    override suspend fun getLatestTv(api_key: String, language: String): List<TvShowResult> {
        return service.getLatestTv(api_key,language)
    }

}