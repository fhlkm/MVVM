package com.doordash.binding.binder.common

interface ItemBinder<T> {
    fun getLayoutRes(model: T): Int
    fun getBindingVariable(model: T): Int
}