package com.doordash.adapter.binder;

import com.doordash.adapter.binder.common.ConditionalDataBinder;
import com.doordash.binding.viewmodel.RestaurantsModel;
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
