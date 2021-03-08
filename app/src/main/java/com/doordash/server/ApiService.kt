package com.doordash.server

import com.doordash.bean.SearchResult
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService private constructor() {
    private val client: OkHttpClient
    private val retrofit: Retrofit
    private val apiService: ApiEndPoints
    private val basicUrl = "https://api.doordash.com/"

    fun getRestaurant(
        lat: Double,
        lng: Double,
        offset: Int,
        limit: Int
    ): Observable<Result<SearchResult>> {
        return apiService.getRestaurants(lat, lng, offset, limit)
    }

    companion object {

        //Thread safe ,https://www.jianshu.com/p/5797b3d0ebd0
        val instance: ApiService by lazy ( mode = LazyThreadSafetyMode.SYNCHRONIZED ){
            ApiService()
        }
    }

    init {
        val logging =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        logging.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder().addInterceptor(logging)
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .build()
        retrofit =
            Retrofit.Builder().baseUrl(basicUrl).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        apiService = retrofit.create(ApiEndPoints::class.java)
    }
}