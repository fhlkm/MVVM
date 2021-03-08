package com.doordash.binding.viewmodel

import com.doordash.bean.Store
import com.doordash.binding.viewmodel.RestaurantsModel.Companion.instance
import org.junit.Assert
import org.junit.Test
import java.util.*

class RestaurantsModelTest {
    @Test
    fun testRestaurantsModel() {
        val restaurantsModel = instance
        val list: MutableList<StoreModel> = ArrayList()
        val m = 9
        for (i in 0..8) {
            val store = Store()
            list.add(StoreModel(store))
        }
        restaurantsModel.addRestaurants(list)
        Assert.assertEquals(m.toLong(), restaurantsModel.size().toLong())
    }
}