package com.binar.melif.data.network.api.service

import com.binar.melif.BuildConfig
import com.binar.melif.data.network.api.model.*
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MelifApiService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieDetail

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tv_id: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): TvShowDetail

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(
        @Path("movie_id") movie_id: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MelifVideo

    @GET("tv/{tv_id}/videos")
    suspend fun getTvShowVideo(
        @Path("tv_id") tv_id: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MelifVideo

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): MelifApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_MELIF)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(MelifApiService::class.java)
        }
    }

}