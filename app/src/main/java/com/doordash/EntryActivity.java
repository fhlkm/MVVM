package com.doordash;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.doordash.adapter.binder.RestaurantBinder;
import com.doordash.adapter.binder.common.CompositeItemBinder;
import com.doordash.adapter.binder.common.ItemBinder;
import com.doordash.model.data.Restaurant;
import com.doordash.model.viewmodel.RestaurantModel;

public class EntryActivity extends AppCompatActivity {
    static String TAG="DD:EntryActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public ItemBinder<RestaurantModel> itemViewBinder()
    {
        return new CompositeItemBinder<RestaurantModel>(
                new RestaurantBinder(BR.restaurants, R.layout.item_restaurant)
        );
    }

    @BindingAdapter("restaurantLogo")
    static void loadImage(ImageView imageView, Restaurant restaurant) {
        String imageUrl;
        imageUrl= restaurant.getStores().get(0).getCoverImgUrl();
        if(!TextUtils.isEmpty(imageUrl)){
            Log.i(TAG,"downloadImage");
            Glide.with(imageView.getContext())
                    .load(imageUrl).apply(new RequestOptions().circleCrop())
                    .into(imageView);

        }else{
            Log.e(TAG,"imageUrl is not valid");
        }
    }
}
