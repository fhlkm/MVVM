package com.doordash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doordash.adapter.ClickHandler
import com.doordash.bean.SearchResult
import com.doordash.bean.Store
import com.doordash.binding.binder.RestaurantBinder
import com.doordash.binding.binder.common.CompositeItemBinder
import com.doordash.binding.binder.common.ItemBinder
import com.doordash.binding.viewmodel.RestaurantsModel
import com.doordash.databinding.BrowseRestaurantBinding
import com.doordash.server.ApiService.Companion.instance
import com.doordash.server.ResultToResponseWithErrorHandlingTransformer
import com.doordash.uitls.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class EntryActivity : AppCompatActivity() {
    //    var restaurantsModel: RestaurantsModel? = null
    lateinit var viewBinding: BrowseRestaurantBinding
    lateinit var disposable: Disposable
    lateinit var model: RestaurantsModel
    var isDownloading = false
    val key = "index"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val restaurants = ObservableArrayList<StoreModel>()
        model = RestaurantsModel.instance
        viewBinding = DataBindingUtil.setContentView(this, R.layout.browse_restaurant)
        viewBinding.mainView = this
        viewBinding.restaurantsViewModel = model
        viewBinding.lifecycleOwner = this
        viewBinding.activityUsersRecycler.layoutManager = LinearLayoutManager(this)

        if (Util.isNetWorkConnected(application)) {
            fetch(0)
        } else {
            Toast.makeText(
                    this@EntryActivity,
                    "Please check Internet connection",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun itemViewBinder(): ItemBinder<Store> {
        return CompositeItemBinder( //BR.bindingstore: viewModel in item_restaurant
                RestaurantBinder(BR.bindingstore, R.layout.item_restaurant)
        )
    }

    // we can move it to view model ,then we don't need to care about memory leak
    private fun fetch(offset: Int) {
        Log.i(TAG, "Search Restaurants")
        isDownloading = true
        val apiService = instance

        disposable = apiService.getRestaurant(37.422740, -122.139956, offset, 20)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(ResultToResponseWithErrorHandlingTransformer())
                .subscribe(
                        { response: Response<*> ->
                            isDownloading = false
                            val searchResult = response.body() as SearchResult
                            val storeList = searchResult.stores
                            Log.i(TAG, "EntryActivity is visiable")
                            model.addRestaurants(storeList)
                        }
                ) { error: Throwable ->
                    Toast.makeText(this@EntryActivity, "Download Failed", Toast.LENGTH_LONG).show()
                    error.printStackTrace()
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        model.clear()
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    var scrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(10)) {
                Log.i(TAG, "loading new items")
                if (!isDownloading && null != model) {
                    fetch(model.size())
                }
            }
        }
    }
    val clickHandler = object: ClickHandler<Store>{
        override fun onClick(storeModel: Store, pos: Int){
            Log.i(TAG, storeModel.name)
            val intent = Intent(this@EntryActivity, RestaurantDetailActivity::class.java)
            intent.putExtra(key, pos)
            startActivity(intent)
        }

    }

    companion object {
        var TAG = "DD:EntryActivity"
    }
}