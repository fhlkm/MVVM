package com.doordash.server

import com.doordash.bean.SearchResult
import com.doordash.server.ApiService.Companion.instance
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class ApiServiceTest {

    @Test
    fun testRestaurantsModel() {

            val apiService = instance
            val disposable = apiService.getRestaurant(37.422740, -122.139956, 0, 20)
                    .compose(ResultToResponseWithErrorHandlingTransformer()).subscribe({ response: Response<*> ->
                        val searchResult = response.body() as SearchResult?
                        val storeList = searchResult!!.stores
                        Assert.assertEquals(20, storeList.size.toLong())
                    }
                    ) { error: Throwable? -> }
    }

}