package com.doordash.binding.binder.common

open abstract class ConditionalDataBinder<T>(bindingVariable: Int, layoutId: Int) : ItemBinderBase<T>(bindingVariable, layoutId) {
    open abstract fun canHandle(model: T): Boolean
    open fun canHandle(layoutId: Int): Boolean {
        return this.layoutId == layoutId
    }
}