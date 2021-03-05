package com.doordash.binding;


public interface ClickHandler<T>
{
    void onClick(T storeModel, int pos);
}