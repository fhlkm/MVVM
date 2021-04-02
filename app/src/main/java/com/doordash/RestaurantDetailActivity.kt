package com.doordash

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.doordash.bean.Store
import com.doordash.binding.binder.MenuBinder
import com.doordash.binding.binder.common.CompositeItemBinder
import com.doordash.binding.binder.common.ItemBinder
import com.doordash.binding.viewmodel.DishModel
import com.doordash.binding.viewmodel.MenuModel
import com.doordash.binding.viewmodel.RestaurantsModel
import com.doordash.databinding.DetailOfRestaurantBinding
import java.util.*

class RestaurantDetailActivity : AppCompatActivity() {
    var TAG = "DD:RestaurantDetailActivity"
    var index = 0
    lateinit var store: Store
    lateinit var detailOfRestaurantBinding: DetailOfRestaurantBinding
    var menuModel = MenuModel()
    val key = "index"
    lateinit var actionBar: ActionBar
    lateinit var model: RestaurantsModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        index = intent.getIntExtra(key, -1)
        Log.i(TAG, "index: $index")
        model = RestaurantsModel.instance
        if (model.size() > 0) {
            detailOfRestaurantBinding = DataBindingUtil.setContentView(this, R.layout.detail_of_restaurant)
            detailOfRestaurantBinding.menuModel = menuModel
            detailOfRestaurantBinding.lifecycleOwner = this
            detailOfRestaurantBinding.restaurantView = this
            detailOfRestaurantBinding.activityUsersRecycler.layoutManager = LinearLayoutManager(
                this
            )
            init()
            actionBar = supportActionBar!!
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = store.name
        }
    }

    fun init() {
        store = model.restaurants[index]
        if(!store.menus.isEmpty()) {
            val items = store.getMenus()[0].popularItems
            val dishlist: MutableList<DishModel> = ArrayList()
            var dishModel: DishModel
            for (item in items) {
                dishModel = DishModel(item)
                dishlist.add(dishModel)
            }
            menuModel.addAll(dishlist)
        }
    }

    fun itemViewBinder(): ItemBinder<DishModel> {
        return CompositeItemBinder( //BR.bindingdish: in item_dish
            MenuBinder(BR.bindingdish, R.layout.item_dish)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}