package com.doordash.binding.binder.common

open class ItemBinderBase<T>( val bindingVariable: Int, protected val layoutId: Int) :
    ItemBinder<T> {
    override fun getLayoutRes(model: T): Int {
        return layoutId
    }

    override fun getBindingVariable(model: T): Int {
        return bindingVariable
    }

}