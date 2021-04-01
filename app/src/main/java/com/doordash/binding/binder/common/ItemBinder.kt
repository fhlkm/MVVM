package com.doordash.binding.binder.common

interface ItemBinder<T> {
    fun getLayoutRes(model: T): Int//R.layout.
    fun getBindingVariable(model: T): Int//BR.x
}