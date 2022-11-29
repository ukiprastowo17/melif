package com.binar.melif.data.network.service

import com.binar.melif.BuildConfig
import com.binar.melif.data.network.model.MovieModel
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
        ): MovieModel

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
        ): MovieModel

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1):MovieModel

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): MovieApiService {
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

            return retrofit.create(MovieApiService::class.java)
        }
    }
}