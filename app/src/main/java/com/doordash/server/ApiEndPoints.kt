package com.doordash.server

import com.doordash.bean.SearchResult
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface ApiEndPoints {
    @Headers(
        "Accept: application/json;charset=utf-8",
        "Content-Type: application/json;charset=utf-8"
    )
    @GET("/v1/store_feed/")
    fun getRestaurants(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Observable<Result<SearchResult>>
}