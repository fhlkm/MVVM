package com.doordash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doordash.EntryActivity
import com.doordash.adapter.ClickHandler
import com.doordash.bean.SearchResult
import com.doordash.binding.binder.RestaurantBinder
import com.doordash.binding.binder.common.CompositeItemBinder
import com.doordash.binding.binder.common.ItemBinder
import com.doordash.binding.viewmodel.RestaurantsModel
import com.doordash.binding.viewmodel.StoreModel
import com.doordash.databinding.BrowseRestaurantBinding
import com.doordash.server.ApiService.Companion.instance
import com.doordash.server.ResultToResponseWithErrorHandlingTransformer
import com.doordash.uitls.Util
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.*

class EntryActivity : AppCompatActivity() {
//    var restaurantsModel: RestaurantsModel? = null
    lateinit var viewBinding: BrowseRestaurantBinding
    lateinit  var disposable: Disposable
    lateinit var model: RestaurantsModel
    var StoreModelList: MutableList<StoreModel> =
        ArrayList()
    var isDownloading = false
    val key = "index"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val restaurants = ObservableArrayList<StoreModel>()
        model = RestaurantsModel.instance
        viewBinding = DataBindingUtil.setContentView(this, R.layout.browse_restaurant)
        viewBinding.setMainView(this)
        viewBinding.setRestaurantsViewModel(model)
        viewBinding.setLifecycleOwner(this)
        viewBinding.activityUsersRecycler.layoutManager = LinearLayoutManager(this)
        if (Util.isNetWorkConnected(application)) {
            search(0)
        } else {
            Toast.makeText(
                this@EntryActivity,
                "Please check Internet connection",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun itemViewBinder(): ItemBinder<StoreModel> {
        return CompositeItemBinder( //BR.bindingstore: viewModel in item_restaurant
            RestaurantBinder(BR.bindingstore, R.layout.item_restaurant)
        )
    }

    // we can move it to view model ,then we don't need to care about memory leak
    private fun search(offset: Int) {
        Log.i(TAG, "Search Restaurants")
        isDownloading = true
        val apiService = instance

        disposable = apiService.getRestaurant(37.422740, -122.139956, offset, 20)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .compose(ResultToResponseWithErrorHandlingTransformer())
            .subscribe(
                { response: Response<*> ->
                    isDownloading = false
                    val searchResult =
                        response.body() as SearchResult?
                    val storeList =
                        searchResult!!.stores
                    val visible =
                        lifecycle.currentState == Lifecycle.State.STARTED || lifecycle.currentState == Lifecycle.State.RESUMED
                    var storeModel: StoreModel
                    for (store in storeList) {
                        storeModel = StoreModel(store)
                        StoreModelList.add(storeModel)
                    }
                    if (visible) {
                        Log.i(TAG, "EntryActivity is visiable")
                        model.addRestaurants(StoreModelList)
                        StoreModelList.clear()
                    }
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
        override fun onScrollStateChanged(
            recyclerView: RecyclerView,
            newState: Int
        ) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(10)) {
                Log.i(TAG, "loading new items")
                if (!isDownloading && null != model) {
                    search(model.size())
                }
            }
        }
    }

    fun clickHandler(): ClickHandler<StoreModel> {
        return ClickHandler<StoreModel> { storeModel, pos ->
            Log.i(TAG, storeModel.storeName)
            val intent = Intent(this@EntryActivity, RestaurantDetailActivity::class.java)
            intent.putExtra(key, pos)
            startActivity(intent)
            //                Toast.makeText(UsersView.this, user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_SHORT).show();
        }
    }

    companion object {
        var TAG = "DD:EntryActivity"
    }
}