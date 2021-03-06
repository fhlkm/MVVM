package com.doordash;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;

import com.doordash.bean.PopularItem;
import com.doordash.binding.binder.common.CompositeItemBinder;
import com.doordash.binding.viewmodel.DishModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.robolectric.Robolectric.buildActivity;

@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "androidx.*", "org.powermock.*", "org.json.*", "javax.crypto.*", "javax.management.*", "com.sun.org.apache.*"})
@PrepareForTest({Log.class})
public class RestaurantDetailActivityTest {
    public static String TAG="DD:RestaurantDetailActivity";
    RestaurantDetailActivity restaurantDetailActivity;
    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockStatic(Log.class);

    }
    @Test
    public void test_launch(){
        Intent intent =new Intent();
        intent.putExtra("index",1);
        restaurantDetailActivity = buildActivity(RestaurantDetailActivity.class,intent).create().get();
        verify(Log.class);
        Log.i(TAG,"index: "+1);

    }

    @Test
    public void itemViewBinderTest(){
        restaurantDetailActivity = buildActivity(RestaurantDetailActivity.class).create().get();
        CompositeItemBinder <DishModel>compositeItemBinder =(CompositeItemBinder<DishModel>) restaurantDetailActivity.itemViewBinder();
        DishModel dishModel=new DishModel(new PopularItem());
        Assert.assertEquals(compositeItemBinder.getLayoutRes(dishModel),R.layout.item_dish);
    }
}
