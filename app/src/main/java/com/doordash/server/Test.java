package com.doordash.server;

import com.doordash.model.data.SearchResult;

import io.reactivex.functions.Consumer;
import retrofit2.Response;

class Test {
    public static void main(String args[]){
        ApiService apiService = ApiService.getInstance();
        apiService.getRestaurant(37.422740,-122.139956,0,1).compose(new ResultToResponseWithErrorHandlingTransformer<>()).subscribe(new Consumer<Response<?>>() {
            @Override
            public void accept(Response<?> response) throws Exception {

               SearchResult searchResult = (SearchResult) response.body();
            }
        });
    }
}
