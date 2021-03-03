package com.doordash;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.doordash.adapter.binder.RestaurantBinder;
import com.doordash.adapter.binder.common.CompositeItemBinder;
import com.doordash.adapter.binder.common.ItemBinder;
import com.doordash.databinding.BrowseRestaurantBindingImpl;
import com.doordash.model.binding.ObservableArrayList;
import com.doordash.model.data.Restaurant;
import com.doordash.model.viewmodel.RestaurantModel;
import com.doordash.model.viewmodel.RestaurantsModel;

public class EntryActivity extends AppCompatActivity {
    static String TAG="DD:EntryActivity";
    RestaurantsModel restaurantsModel;
    BrowseRestaurantBindingImpl viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObservableArrayList<RestaurantModel> restaurants =new ObservableArrayList<>();
        RestaurantsModel model=new RestaurantsModel(restaurants);
        viewBinding=   DataBindingUtil.setContentView(this, R.layout.browse_restaurant);
        viewBinding.setView(this);
        viewBinding.setRestaurantsViewModel(model);
        viewBinding.setLifecycleOwner(this);
        viewBinding.activityUsersRecycler.setLayoutManager(new LinearLayoutManager(this));

    }
    public ItemBinder<RestaurantModel> itemViewBinder()
    {
        return new CompositeItemBinder<RestaurantModel>(
                new RestaurantBinder(BR.restaurants, R.layout.item_restaurant)
        );
    }

    @BindingAdapter("restaurantLogo")
    public static void setRestaurantLogo(ImageView imageView, Restaurant restaurant) {
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
