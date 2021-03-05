package com.doordash.binding.viewmodel;

import androidx.databinding.BaseObservable;

import com.doordash.model.data.PopularItem;

public class DishModel extends BaseObservable {

    PopularItem item;

    public DishModel(PopularItem item) {
        this.item = item;
    }

    public String getDishImageUrl(){
        return item.getImgUrl();
    }
    public String getDishName(){
        return item.getName();
    }

    public String getDishDesciption(){
        return item.getDescription();
    }
}
