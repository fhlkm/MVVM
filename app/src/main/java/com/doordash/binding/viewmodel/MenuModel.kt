package com.doordash.binding.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList

class MenuModel : BaseObservable() {
    @JvmField
    @Bindable
    var dishes = ObservableArrayList<DishModel>()
    fun addAll(list: List<DishModel>) {
        dishes.addAll(list!!)
    }
}