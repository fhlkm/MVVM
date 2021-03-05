package com.doordash.binding.binder;

import com.doordash.binding.binder.common.ConditionalDataBinder;
import com.doordash.binding.viewmodel.StoreModel;


public class RestaurantBinder extends ConditionalDataBinder<StoreModel>
{
    public RestaurantBinder(int bindingVariable, int layoutId)
    {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(StoreModel model) {
        return true;
    }


}
