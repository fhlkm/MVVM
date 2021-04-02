package com.doordash.binding.binder

import com.doordash.bean.PopularItem
import com.doordash.binding.binder.common.ConditionalDataBinder

class MenuBinder(bindingVariable: Int, layoutId: Int) :
    ConditionalDataBinder<PopularItem>(bindingVariable, layoutId) {
    override fun canHandle(model: PopularItem): Boolean {
        return true
    }
}