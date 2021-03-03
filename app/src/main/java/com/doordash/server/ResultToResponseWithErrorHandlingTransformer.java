package com.doordash.server;


import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

public class ResultToResponseWithErrorHandlingTransformer<T> implements ObservableTransformer<Result<T>, Response<?>> {
    String TAG ="ResultToResponseWithErrorHandlingTransformer";
    @Override
    public ObservableSource<Response<?>> apply(Observable<Result<T>> upstream) {
       return upstream.flatMap(result -> {
            if ( result.response() == null) {
                Log.e(TAG,"checkResultSuccess...result is empty");
                if ( result.error() != null) {
                    Log.e(TAG,result.error().toString());
                }
                return  Observable.error(new WebSdkErrorException("EmptyBody"));
            }

            if (result.isError()) {
                Log.e(TAG,"checkResultSuccess..." + result.isError());
                return  Observable.error(new WebSdkErrorException(result.error().getMessage()));
            }

            if (result.response().isSuccessful()) {
                Log.d(TAG,"checkResultSuccess..." + result.response().isSuccessful());
                Log.d(TAG,"checkResultSuccess...code..." + result.response().code());
                return Observable.just(result.response());
            } else {
                Log.e(TAG,"checkResultSuccess..." + result.response().isSuccessful());

                int httpCode = result.response().code();
                Log.e(TAG,"checkResultSuccess...httpCode..." + httpCode);
                String errorBodyString= result.response().errorBody().string();
                return Observable.error(new WebSdkErrorException(errorBodyString));
            }

        });
    }
}
