package com.doordash.binding.viewmodel

import androidx.databinding.BaseObservable
import com.doordash.bean.PopularItem

class DishModel(var item: PopularItem) : BaseObservable() {
    val dishImageUrl: String
        get() = item.imgUrl

    val dishName: String
        get() = item.name

    val dishDesciption: String
        get() = item.description

}