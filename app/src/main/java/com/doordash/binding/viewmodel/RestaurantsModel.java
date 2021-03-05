package com.doordash.binding.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;


import java.util.List;

public class RestaurantsModel extends BaseObservable {

    @Bindable
    public ObservableArrayList<StoreModel> restaurants;

    private static RestaurantsModel restaurantsModel;
    public static RestaurantsModel getInstance(){
        if(null==restaurantsModel){
            restaurantsModel = new RestaurantsModel();
            restaurantsModel.restaurants =new ObservableArrayList<>();
        }
        return restaurantsModel;
    }

//    public RestaurantsModel(ObservableArrayList<StoreModel> restaurants) {
//        if(null == restaurants)
//        this.restaurants = restaurants;
//    }

    public void addRestaurants(List<StoreModel> list){
        restaurants.addAll(list);
    }
    public void clear(){
        restaurants.clear();
    }

    public int size(){
        return restaurants.size();
    }


}
