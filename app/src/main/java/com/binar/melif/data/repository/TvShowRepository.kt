package com.binar.melif.data.repository

import com.binar.melif.base.BaseRepository
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.network.api.datasource.TvShowApiDataSource
import com.binar.melif.data.network.model.TvShowResult

interface TvShowRepository {
    suspend fun getTopRatedTvList(api_key:String = "d5524f7d769b5bb3c30b5abbf9706aa3",
                              language: String = "en-Us",
                              page:Int = 1) : Resource<List<TvShowResult>>

    suspend fun getPopularTvList(api_key:String = "d5524f7d769b5bb3c30b5abbf9706aa3",
                             language: String = "en-Us",
                             page:Int = 1) : Resource<List<TvShowResult>>

    suspend fun getLatestTvList(api_key:String = "d5524f7d769b5bb3c30b5abbf9706aa3",
                            language: String = "en-Us") : Resource<List<TvShowResult>>
}

class TvShowRepositoryImpl(private val networkDataSource: TvShowApiDataSource): TvShowRepository,BaseRepository(){
    override suspend fun getTopRatedTvList(
        api_key: String,
        language: String,
        page: Int
    ): Resource<List<TvShowResult>> {
        return doNetworkCall { networkDataSource.getTopRatedTv(api_key, language, page)  }
    }

    override suspend fun getPopularTvList(
        api_key: String,
        language: String,
        page: Int
    ): Resource<List<TvShowResult>> {
        return doNetworkCall { networkDataSource.getPopularTv(api_key, language, page)  }
    }

    override suspend fun getLatestTvList(api_key: String, language: String): Resource<List<TvShowResult>> {
        return doNetworkCall { networkDataSource.getLatestTv(api_key, language)  }
    }


}