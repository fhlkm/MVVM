package com.doordash.binding

import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doordash.adapter.BindingRecyclerViewAdapter
import com.doordash.adapter.ClickHandler
import com.doordash.binding.binder.common.ItemBinder

object RecyclerViewBindings {
    var TAG = "DD:RecyclerViewBindings"
    private const val KEY_ITEMS = -123
    private const val KEY_CLICK_HANDLER = -124

    @JvmStatic
    @BindingAdapter("items")
    fun <T> setItems(recyclerView: RecyclerView, items: Collection<T>) {
        Log.i(TAG, "setItems")
        val adapter = recyclerView.adapter as BindingRecyclerViewAdapter<T>?
        if (null != adapter) {
            Log.i(TAG, "setItems to Adapter")
            adapter.setItems(items)
        } else {
            //in case adapter is init late
            recyclerView.setTag(KEY_ITEMS, items)
        }
    }

    @JvmStatic
    @BindingAdapter("itemViewBinder")
    fun <T> setItemViewBinder(recyclerView: RecyclerView, itemViewMapper: ItemBinder<T>) {
        val items = recyclerView.getTag(KEY_ITEMS) as? Collection<T>
        val clickHandler = recyclerView.getTag(KEY_CLICK_HANDLER) as? ClickHandler<T>
        Log.i(TAG, "setItemViewBinder")
        //  RestaurantBinder(BR.bindingstore, R.layout.item_restaurant)
        //  MenuBinder(BR.bindingdish, R.layout.item_dish)
        val adapter = BindingRecyclerViewAdapter(itemViewMapper, items)
        recyclerView.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("scrollListener")
    fun scrollListener(recyclerView: RecyclerView, listener: RecyclerView.OnScrollListener?) {
        Log.i(TAG, "scrollListener")
        if (null != listener) {
            recyclerView.addOnScrollListener(listener)
        } else {
            Log.i(TAG, "scrollListener in null")
        }
    }

    @JvmStatic
    @BindingAdapter("clickHandler")
    fun <T> setHandler(recyclerView: RecyclerView, handler: ClickHandler<T>) {
        val adapter = recyclerView.adapter as BindingRecyclerViewAdapter<T>
        if (adapter != null) {
            adapter.setClickHandler(handler)
        } else {
            recyclerView.setTag(KEY_CLICK_HANDLER, handler)
        }
    }

    @JvmStatic
    @BindingAdapter("restaurantLogo")
    fun setRestaurantLogo(imageView: ImageView, imageUrl: String?) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        } else {
            Log.e(TAG, "imageUrl is not valid")
        }
    }
}