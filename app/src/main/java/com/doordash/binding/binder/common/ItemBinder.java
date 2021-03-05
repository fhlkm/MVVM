package com.doordash.binding.binder.common;

public interface ItemBinder<T>
{
      int getLayoutRes(T model);
      int getBindingVariable(T model);
}
