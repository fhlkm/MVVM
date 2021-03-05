package com.doordash.binding.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;


import java.util.List;

public class MenuModel extends BaseObservable {

    @Bindable
    public ObservableArrayList<DishModel> dishes=new ObservableArrayList<>();

    public MenuModel(){
    }

    public void addAll(List<DishModel> list){
        dishes.addAll(list);
    }


}
