package com.doordash.binding.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList

class RestaurantsModel private constructor() : BaseObservable() {
    @JvmField
    @Bindable
    var restaurants: ObservableArrayList<StoreModel> = ObservableArrayList()

    fun addRestaurants(list: List<StoreModel>) {
        restaurants.addAll(list!!)
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