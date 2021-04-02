package com.doordash.binding.binder

import com.doordash.bean.Store
import com.doordash.binding.binder.common.ConditionalDataBinder

class RestaurantBinder(bindingVariable: Int, layoutId: Int) : ConditionalDataBinder<Store>(bindingVariable, layoutId) {


    override fun canHandle(model: Store): Boolean {
      return true
    }
}