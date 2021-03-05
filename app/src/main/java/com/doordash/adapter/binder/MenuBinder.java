package com.doordash.adapter.binder;

import com.doordash.adapter.binder.common.ConditionalDataBinder;
import com.doordash.binding.viewmodel.DishModel;

public class MenuBinder extends ConditionalDataBinder<DishModel> {
    /**
     *
     * @param bindingVariable
     * @param layoutId
     */
    public MenuBinder(int bindingVariable, int layoutId){
        super(bindingVariable,layoutId);
    }

    @Override
    public boolean canHandle(DishModel model) {
        return true;
    }

}
