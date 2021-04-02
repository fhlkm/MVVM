package com.doordash.binding.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.doordash.bean.Store

class RestaurantsModel private constructor() : BaseObservable() {

    var restaurants: ObservableArrayList<Store> = ObservableArrayList()////Bindable should move to get, works with   notifyPropertyChanged()

    fun addRestaurants(list: List<Store>) {
        restaurants.addAll(list)
    }

    fun clear() {
        restaurants.clear()
    }

    fun size(): Int {
        return restaurants.size
    }

    companion object {
        val instance:RestaurantsModel by lazy (mode= LazyThreadSafetyMode.SYNCHRONIZED ){
            RestaurantsModel();
        }
    }
}