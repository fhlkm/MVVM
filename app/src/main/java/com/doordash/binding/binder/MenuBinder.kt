package com.doordash.binding.binder

import com.doordash.binding.binder.common.ConditionalDataBinder
import com.doordash.binding.viewmodel.DishModel

class MenuBinder(bindingVariable: Int, layoutId: Int) :
    ConditionalDataBinder<DishModel>(bindingVariable, layoutId) {
    override fun canHandle(model: DishModel): Boolean {
        return true
    }
}