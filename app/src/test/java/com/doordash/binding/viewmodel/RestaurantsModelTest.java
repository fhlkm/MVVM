package com.doordash.binding.viewmodel;

import com.doordash.bean.Store;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsModelTest {
     @Test
    public void testRestaurantsModel(){

         RestaurantsModel restaurantsModel=RestaurantsModel.getInstance();
         List<StoreModel> list =new ArrayList<>();
         int m=9;
         for(int i=0;i<9;i++){
             Store store =new Store();
             list.add(new StoreModel(store));
         }
         restaurantsModel.addRestaurants(list);
         Assert.assertEquals(m,restaurantsModel.size() );

     }
}
