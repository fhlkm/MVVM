package com.doordash.model.viewmodel;

import androidx.databinding.Bindable;

import com.doordash.BR;
import com.doordash.R;
import com.doordash.adapter.binder.RestaurantBinder;
import com.doordash.adapter.binder.common.CompositeItemBinder;
import com.doordash.adapter.binder.common.ItemBinder;
import com.doordash.model.binding.ObservableArrayList;
import com.doordash.model.data.Restaurant;

import java.util.List;

public class RestaurantsModel extends BaseObservable{

    @Bindable
    public ObservableArrayList<RestaurantModel> restaurants;

    public RestaurantsModel(ObservableArrayList<RestaurantModel> restaurants) {
        this.restaurants = restaurants;
    }

    public void addRestaurants(List<Restaurant> list){
        ObservableArrayList<RestaurantModel> temp=new ObservableArrayList<>();
        RestaurantModel restaurantModel;
        for(Restaurant restaurant: list){
            restaurantModel=new RestaurantModel(restaurant);
            temp.add(restaurantModel);
        }
        restaurants.addAll(temp);
    }


}
