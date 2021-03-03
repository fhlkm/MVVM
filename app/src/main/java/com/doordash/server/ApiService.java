package com.doordash.server;



import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private OkHttpClient client;
    private Retrofit retrofit;
    private ApiEndPoints apiService;

    private String basicUrl = "https://swapi.dev/api/";
    public volatile static ApiService instance;
    public static ApiService getInstance(){
        if(null == instance)
            synchronized (ApiService.class) {
                if(null==instance)
                instance = new ApiService();
            }

        return  instance;
    }

    private ApiService(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor( HttpLoggingInterceptor.Logger.DEFAULT);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        this.client = new OkHttpClient.Builder().addInterceptor(logging)
                .connectTimeout(25, TimeUnit.SECONDS)
                .readTimeout(25, TimeUnit.SECONDS)
                .build();
        this.retrofit = new Retrofit.Builder().baseUrl(basicUrl).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        apiService = retrofit.create(ApiEndPoints.class);
    }

//    public Observable<Result<SearchResponse>> search(String  key){
//        return  apiService.search(key);
//    }
//
//    public Observable<Result<Film>> getFilm(String  url){
//        return  apiService.getFilm(url);
//    }


}
