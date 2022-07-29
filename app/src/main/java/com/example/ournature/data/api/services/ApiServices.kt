package com.example.ournature.data.api.services

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.ournature.data.api.response.NewsListResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiServices {
    @GET("everything")
    suspend fun getNewsList(
        @Query("q") q: String = "nature",
        @Query("pageSize") ps: Int = 20,
        @Query("apiKey") key: String = "20f6ba4adf3547a7a0dfffae4d1d0ba5"
    ): NewsListResponse

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): ApiServices {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiServices::class.java)
        }
    }
}