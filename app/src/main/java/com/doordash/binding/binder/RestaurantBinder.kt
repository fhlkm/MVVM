package com.doordash.binding.binder

import com.doordash.binding.binder.common.ConditionalDataBinder
import com.doordash.binding.viewmodel.StoreModel

class RestaurantBinder(bindingVariable: Int, layoutId: Int) :
    ConditionalDataBinder<StoreModel>(bindingVariable, layoutId) {


    override fun canHandle(model: StoreModel): Boolean {
      return true
    }
}