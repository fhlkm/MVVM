package com.doordash.binding.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.doordash.bean.PopularItem

class MenuModel : ViewModel() {
    var dishes = ObservableArrayList<PopularItem>()//Bindable should move to get, works with   notifyPropertyChanged()
    fun addAll(list: List<PopularItem>) {
        dishes.addAll(list!!)
    }
}