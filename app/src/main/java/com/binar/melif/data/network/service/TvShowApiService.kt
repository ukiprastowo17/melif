package com.binar.melif.data.network.service

import com.binar.melif.BuildConfig
import com.binar.melif.data.network.model.TvShowResult
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface TvShowApiService {
    @GET("3/tv/top_rated")
    suspend fun getTopRatedTv(
        @Query("api_key") api_key:String = "d5524f7d769b5bb3c30b5abbf9706aa3",
        @Query("language") language: String = "en-Us",
        @Query("page")page:Int = 1) : List<TvShowResult>

    @GET("3/tv/popular")
    suspend fun getPopulerTv(
        @Query("api_key") api_key:String = "d5524f7d769b5bb3c30b5abbf9706aa3",
        @Query("language") language: String = "en-Us",
        @Query("page")page:Int = 1) : List<TvShowResult>

    @GET("3/tv/latest")
    suspend fun getLatestTv(
        @Query("api_key") api_key:String = "d5524f7d769b5bb3c30b5abbf9706aa3",
        @Query("language") language: String = "en-Us") : List<TvShowResult>

    companion object{
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor):TvShowApiService{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_MELIF)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(TvShowApiService::class.java)
        }
    }
}