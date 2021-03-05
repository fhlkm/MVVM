package com.doordash;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.doordash.binding.binder.MenuBinder;
import com.doordash.binding.binder.common.CompositeItemBinder;
import com.doordash.binding.binder.common.ItemBinder;
import com.doordash.databinding.DetailOfRestaurantBinding;
import com.doordash.bean.PopularItem;
import com.doordash.bean.Store;
import com.doordash.binding.viewmodel.DishModel;
import com.doordash.binding.viewmodel.MenuModel;
import com.doordash.binding.viewmodel.RestaurantsModel;
import com.doordash.binding.viewmodel.StoreModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailActivity extends AppCompatActivity {
    public String TAG="DD:RestaurantDetailActivity";
    int index=0;
    Store store;
    DetailOfRestaurantBinding detailOfRestaurantBinding;
    MenuModel menuModel=new MenuModel();
    final String key="index";
    ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index =getIntent().getIntExtra(key,-1);
        Log.i(TAG,"index: "+index);
        setContentView(R.layout.detail_of_restaurant);
        detailOfRestaurantBinding=DataBindingUtil.setContentView(this,R.layout.detail_of_restaurant);
        detailOfRestaurantBinding.setMenuModel(menuModel);
        detailOfRestaurantBinding.setLifecycleOwner(this);
        detailOfRestaurantBinding.setRestaurantView(this);
        detailOfRestaurantBinding.activityUsersRecycler.setLayoutManager(new LinearLayoutManager(this));

        init();
        actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(store.getName());
    }


    public void init(){
        RestaurantsModel model=RestaurantsModel.getInstance();
        StoreModel storeModel= model.restaurants.get(index);
        store = storeModel.getStore();
        List<PopularItem> items = store.getMenus().get(0).getPopularItems();
        List<DishModel> dishlist=new ArrayList<>();
        DishModel dishModel;
        for(PopularItem item:items){
            dishModel =new DishModel(item);
            dishlist.add(dishModel);
        }
        menuModel.addAll(dishlist);
    }

    public ItemBinder<DishModel> itemViewBinder()
    {
        return new CompositeItemBinder<DishModel>(
                //BR.bindingdish: in item_dish
                new MenuBinder(BR.bindingdish, R.layout.item_dish)
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
