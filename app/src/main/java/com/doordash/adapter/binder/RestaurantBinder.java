package com.doordash.adapter.binder;

import com.doordash.adapter.binder.common.ConditionalDataBinder;
import com.doordash.model.viewmodel.RestaurantModel;


public class RestaurantBinder extends ConditionalDataBinder<RestaurantModel>
{
    public RestaurantBinder(int bindingVariable, int layoutId)
    {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(RestaurantModel model)
    {
        return true;
    }
}
