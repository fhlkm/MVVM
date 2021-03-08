package com.doordash

import android.content.Intent
import android.util.Log
import com.doordash.bean.PopularItem
import com.doordash.binding.binder.common.CompositeItemBinder
import com.doordash.binding.viewmodel.DishModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*", "androidx.*", "org.powermock.*", "org.json.*", "javax.crypto.*", "javax.management.*", "com.sun.org.apache.*")
@PrepareForTest(Log::class)
class RestaurantDetailActivityTest {
    var restaurantDetailActivity: RestaurantDetailActivity? = null

    @Rule
    @JvmField
     var rule = PowerMockRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        PowerMockito.mockStatic(Log::class.java)
    }

    @Test
    fun test_launch() {
        val intent = Intent()
        intent.putExtra("index", 1)
        restaurantDetailActivity = Robolectric.buildActivity(RestaurantDetailActivity::class.java, intent).create().get()
        Mockito.verify(Log::class.java)
        Log.i(TAG, "index: " + 1)
    }

    @Test
    fun itemViewBinderTest() {
        restaurantDetailActivity = Robolectric.buildActivity(RestaurantDetailActivity::class.java).create().get()
        val compositeItemBinder = restaurantDetailActivity!!.itemViewBinder() as CompositeItemBinder<DishModel>
        val dishModel = DishModel(PopularItem())
        Assert.assertEquals(compositeItemBinder.getLayoutRes(dishModel).toLong(), R.layout.item_dish.toLong())
    }

    companion object {
        var TAG = "DD:RestaurantDetailActivity"
    }
}