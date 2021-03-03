package com.doordash.server;


import com.doordash.model.data.Restaurant;


import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ApiEndPoints {


    //    @Headers({"Accept: application/json;charset=utf-8", "Content-Type: application/json;charset=utf-8"})
//    @GET("people/")
//    @NotNull
//    Observable<Result<SearchResponse>> search(@Query("search") String key);
//
//
//    @Headers({"Accept: application/json;charset=utf-8", "Content-Type: application/json;charset=utf-8"})
//    @GET
//    @NotNull
//    Observable<Result<Film>> getFilm(@Url String url);
    @Headers({"Accept: application/json;charset=utf-8", "Content-Type: application/json;charset=utf-8"})
    @GET("/v1/store_feed/")
    Observable<Result<Restaurant>> getRestaurants(@Query("lat") double lat, @Query("lng") double lng,  @Query("offset") int offset,@Query("limit") int limit );

}
