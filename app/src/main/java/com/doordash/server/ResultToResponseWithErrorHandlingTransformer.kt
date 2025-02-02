package com.doordash.server

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result


class ResultToResponseWithErrorHandlingTransformer<T>: ObservableTransformer<Result<T>, Response<*>> {
    val TAG:String = ResultToResponseWithErrorHandlingTransformer::class.java.name

    override fun apply(upstream: Observable<Result<T>>?): ObservableSource<Response<*>> {

        return upstream!!.flatMap(Function<Result<*>, Observable<Response<*>>> { result ->
            if ( result.response() == null) {
                Log.e(TAG,"checkResultSuccess...result is empty")
                if ( result.error() != null) {
                    Log.e(TAG,result.error()!!.toString())
                }
                return@Function Observable.error<Response<*>>(WebSdkErrorException("EmptyBody"))
            }
            if (result.isError) {
                Log.e(TAG,"checkResultSuccess..." + result.isError)
                return@Function Observable.error<Response<*>>(WebSdkErrorException(result!!.error()!!.message!!))
            }

            if (result.response()!!.isSuccessful) {
                Log.d(TAG,"checkResultSuccess..." + result.response()!!.isSuccessful)
                Log.d(TAG,"checkResultSuccess...code..." + result.response()!!.code())
                Observable.just(result.response()!!)
            } else {
                Log.e(TAG,"checkResultSuccess..." + result.response()!!.isSuccessful)

                val httpCode = result.response()!!.code()
                Log.e(TAG,"checkResultSuccess...httpCode..." + httpCode)
                var errorBodyString= result.response()!!.errorBody()!!.string()
                return@Function Observable.error<Response<*>>(WebSdkErrorException(errorBodyString))
            }
        })
    }
}

