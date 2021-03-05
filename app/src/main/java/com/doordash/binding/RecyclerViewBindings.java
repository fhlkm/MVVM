package com.doordash.binding;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doordash.adapter.binder.common.ItemBinder;

import java.util.Collection;

public class RecyclerViewBindings {
    static String TAG="DD:RecyclerViewBindings";
    private static final int KEY_ITEMS = -123;
    private static final int KEY_CLICK_HANDLER = -124;
    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, Collection<T>items){
        Log.i(TAG,"setItems");
        BindingRecyclerViewAdapter<T> adapter =(  BindingRecyclerViewAdapter<T>)recyclerView.getAdapter();
        if(null !=adapter){
            Log.i(TAG,"setItems to Adapter");
            adapter.setItems(items);
        } else
        {
            //in case adapter is init late
            recyclerView.setTag(KEY_ITEMS, items);
        }
    }


    @BindingAdapter("itemViewBinder")
    public static <T> void setItemViewBinder(RecyclerView recyclerView, ItemBinder<T> itemViewMapper)
    {
        Collection<T> items = (Collection<T>) recyclerView.getTag(KEY_ITEMS);
        ClickHandler<T> clickHandler = (ClickHandler<T>) recyclerView.getTag(KEY_CLICK_HANDLER);
        Log.i(TAG,"setItemViewBinder");
        BindingRecyclerViewAdapter<T> adapter = new BindingRecyclerViewAdapter<>(itemViewMapper, items);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("scrollListener")
    public static void scrollListener(RecyclerView recyclerView, RecyclerView.OnScrollListener listener){
        Log.i(TAG,"scrollListener");
        if(null!= listener) {
            recyclerView.addOnScrollListener(listener);
        }else{
            Log.i(TAG,"scrollListener in null");
        }
    }

    @BindingAdapter("clickHandler")
    public static <T> void setHandler(RecyclerView recyclerView, ClickHandler<T> handler)
    {
        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null)
        {
            adapter.setClickHandler(handler);
        }
        else
        {
            recyclerView.setTag(KEY_CLICK_HANDLER, handler);
        }
    }

    @BindingAdapter("restaurantLogo")
    public static void setRestaurantLogo(ImageView imageView, String  imageUrl) {

        if(!TextUtils.isEmpty(imageUrl)){
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .into(imageView);

        }else{
            Log.e(TAG,"imageUrl is not valid");
        }
    }

}
