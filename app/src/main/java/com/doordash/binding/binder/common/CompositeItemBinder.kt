package com.doordash.binding.binder.common

class CompositeItemBinder<T>(vararg conditionalDataBinders: ConditionalDataBinder<T>) : ItemBinder<T> {
    private val conditionalDataBinders: Array<ConditionalDataBinder<T>>
    override fun getLayoutRes(model: T): Int {
        for (i in conditionalDataBinders.indices) {
            val dataBinder = conditionalDataBinders[i]
            if (dataBinder.canHandle(model)) {
                return dataBinder.getLayoutRes(model)
            }
        }
        throw IllegalStateException()
    }

    //BR.x
    override fun getBindingVariable(model: T): Int {
        for (i in conditionalDataBinders.indices) {
            val dataBinder = conditionalDataBinders[i]
            if (dataBinder.canHandle(model)) {
                return dataBinder.getBindingVariable(model)
            }
        }
        throw IllegalStateException()
    }

    init {
        this.conditionalDataBinders = conditionalDataBinders as Array<ConditionalDataBinder<T>>
    }
}