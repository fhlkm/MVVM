package com.doordash.binding.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import com.doordash.bean.PopularItem

class MenuModel : BaseObservable() {
    @JvmField
    @Bindable
    var dishes = ObservableArrayList<PopularItem>()//Bindable should move to get, works with   notifyPropertyChanged()
    fun addAll(list: List<PopularItem>) {
        dishes.addAll(list!!)
    }
}