package com.doordash.binding.viewmodel

import androidx.databinding.BaseObservable
import com.doordash.bean.Store

class StoreModel(var store: Store) : BaseObservable() {

    val imageUrl: String
        get() = store.coverImgUrl

    val storeName: String
        get() = store.name

    val storeDescription: String
        get() = store.description

}