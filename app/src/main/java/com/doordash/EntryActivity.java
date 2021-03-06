package com.doordash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.doordash.binding.binder.RestaurantBinder;
import com.doordash.binding.binder.common.CompositeItemBinder;
import com.doordash.binding.binder.common.ItemBinder;
import com.doordash.adapter.ClickHandler;

import com.doordash.bean.SearchResult;
import com.doordash.bean.Store;
import com.doordash.binding.viewmodel.StoreModel;
import com.doordash.binding.viewmodel.RestaurantsModel;
import com.doordash.databinding.BrowseRestaurantBinding;

import com.doordash.server.ApiService;
import com.doordash.server.ResultToResponseWithErrorHandlingTransformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class EntryActivity extends AppCompatActivity {
    static String TAG="DD:EntryActivity";
    RestaurantsModel restaurantsModel;
   BrowseRestaurantBinding viewBinding;
    Disposable disposable;
    RestaurantsModel model;
    List<StoreModel> StoreModelList =new ArrayList<>();
    boolean isDownloading=false;
    final String key="index";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObservableArrayList<StoreModel> restaurants =new ObservableArrayList<>();
        model=RestaurantsModel.getInstance();
        viewBinding=   DataBindingUtil.setContentView(this, R.layout.browse_restaurant);
        viewBinding.setMainView(this);
        viewBinding.setRestaurantsViewModel(model);
        viewBinding.setLifecycleOwner(this);
        viewBinding.activityUsersRecycler.setLayoutManager(new LinearLayoutManager(this));
        search(0);

    }
    public ItemBinder<StoreModel> itemViewBinder()
    {
        return new CompositeItemBinder<StoreModel>(
                //BR.bindingstore: viewModel in item_restaurant
                new RestaurantBinder(BR.bindingstore, R.layout.item_restaurant)
        );
    }



    private void search(int offset){
        Log.i(TAG,"Search Restaurants");
        isDownloading=true;
        ApiService apiService = ApiService.getInstance();


        disposable = apiService.getRestaurant(37.422740,-122.139956,offset,20).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(new ResultToResponseWithErrorHandlingTransformer<>()).subscribe(response -> {
                            isDownloading=false;
                            SearchResult searchResult = (SearchResult) response.body();
                            List<Store> storeList = searchResult.getStores();
                            boolean visible= getLifecycle().getCurrentState().equals(Lifecycle.State.STARTED)||getLifecycle().getCurrentState().equals(Lifecycle.State.RESUMED);
                            StoreModel storeModel;
                            for(Store store: storeList) {
                                storeModel= new StoreModel(store);
                                StoreModelList.add(storeModel);
                            }
                            if(visible){
                                Log.i(TAG,"EntryActivity is visiable");
                                model.addRestaurants(StoreModelList);
                                StoreModelList.clear();
                            }
                        },
                        error->{
                            Toast.makeText(EntryActivity.this,"Download Failed",Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.clear();
        if(disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
    RecyclerView .OnScrollListener listener =new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (!recyclerView.canScrollVertically(10)) {
                Log.i(TAG,"loading new items");
                if(!isDownloading&& null != model){
                    search(model.size());
                }

            }
        }
    };
    public RecyclerView .OnScrollListener getScrollListener(){
      return listener;
    }

    public ClickHandler<StoreModel> clickHandler()
    {
        return new ClickHandler<StoreModel>()
        {
            @Override
            public void onClick(StoreModel storeModel,int pos)
            {
                Log.i(TAG,storeModel.getStoreName());
                Intent intent = new Intent(EntryActivity.this, RestaurantDetailActivity.class);
                intent.putExtra(key,pos);
                startActivity(intent);
//                Toast.makeText(UsersView.this, user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_SHORT).show();
            }
        };
    }

}
