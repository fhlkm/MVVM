package com.doordash.model.binding;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;

public class RecyclerViewBindings {
    static String TAG="DD:RecyclerViewBindings";

    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, Collection<T>items){

        BindingRecyclerViewAdapter<T> adapter =(  BindingRecyclerViewAdapter<T>)recyclerView.getAdapter();
        if(null ==adapter){
            Log.i(TAG,"setItems to Adapter");
            adapter.setItems(items);
        }

    }
}
