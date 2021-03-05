package com.doordash.adapter;


public interface ClickHandler<T>
{
    void onClick(T storeModel, int pos);
}