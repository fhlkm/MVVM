package com.doordash.binding.viewmodel;

import androidx.databinding.BaseObservable;

import com.doordash.bean.Store;

public class StoreModel extends BaseObservable {
    private Store store;

    public StoreModel(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getImageUrl(){
        return store.getCoverImgUrl();
    }

    public String getStoreName(){
        return store.getName();
    }

    public String getStoreDescription(){
        return store.getDescription();
    }
}
