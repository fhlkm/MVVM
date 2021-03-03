package com.doordash.server;

import com.doordash.model.data.Restaurant;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

 class Test {
    public static void main(String args[]){
        ApiService apiService = ApiService.getInstance();
        apiService.getRestaurant(37.422740,-122.139956,0,1).compose(new ResultToResponseWithErrorHandlingTransformer<>()).subscribe(new Consumer<Response<?>>() {
            @Override
            public void accept(Response<?> response) throws Exception {

               Restaurant restaurant= (Restaurant) response.body();
            }
        });
    }
}
