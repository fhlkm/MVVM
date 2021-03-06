package com.doordash;

import android.util.Log;

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
        restaurantDetailActivity = buildActivity(RestaurantDetailActivity.class).create().get();
        verify(Log.class);
        Log.i(TAG,"index: "+(-1));

    }
}
