package com.doordash.model.viewmodel;

import com.doordash.model.data.Restaurant;

public class RestaurantModel extends BaseObservable{
    private Restaurant restaurant;

    public RestaurantModel(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
