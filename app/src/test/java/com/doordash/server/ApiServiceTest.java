package com.doordash.server;

import com.doordash.bean.SearchResult;
import com.doordash.bean.Store;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ApiServiceTest {

    @Test
    public void getRestaurantTest(){
        ApiService apiService = ApiService.getInstance();

      Disposable disposable = apiService.getRestaurant(37.422740,-122.139956,0,20)
                .compose(new ResultToResponseWithErrorHandlingTransformer<>()).subscribe(response -> {
                            SearchResult searchResult = (SearchResult) response.body();
                            List<Store> storeList = searchResult.getStores();
                          Assert.assertEquals(20,storeList.size());
                        },
                        error->{

                        });
    }
}
