package com.doordash.binding.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;


import java.util.List;

public class RestaurantsModel extends BaseObservable {

    @Bindable
    public  ObservableArrayList<StoreModel> restaurants;

    private volatile  static RestaurantsModel instance;
    public static RestaurantsModel getInstance(){
        if(null== instance){
            synchronized (RestaurantsModel.class) {
                if(null == instance) {
                    instance = new RestaurantsModel();
                    instance.restaurants = new ObservableArrayList<>();
                }
            }
        }
        return instance;
    }
    private RestaurantsModel(){

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
