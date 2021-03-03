package com.doordash.model.binding;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.doordash.adapter.binder.common.ItemBinder;

import java.util.Collection;

public class RecyclerViewBindings {
    static String TAG="DD:RecyclerViewBindings";
    private static final int KEY_ITEMS = -123;
    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, Collection<T>items){
        Log.i(TAG,"setItems");
        BindingRecyclerViewAdapter<T> adapter =(  BindingRecyclerViewAdapter<T>)recyclerView.getAdapter();
        if(null !=adapter){
            Log.i(TAG,"setItems to Adapter");
            adapter.setItems(items);
        } else
        {
            recyclerView.setTag(KEY_ITEMS, items);
        }
    }


    @BindingAdapter("itemViewBinder")
    public static <T> void setItemViewBinder(RecyclerView recyclerView, ItemBinder<T> itemViewMapper)
    {
        Collection<T> items = (Collection<T>) recyclerView.getTag(KEY_ITEMS);
        Log.i(TAG,"setItemViewBinder");
        BindingRecyclerViewAdapter<T> adapter = new BindingRecyclerViewAdapter<>(itemViewMapper, items);
        recyclerView.setAdapter(adapter);
    }
}
