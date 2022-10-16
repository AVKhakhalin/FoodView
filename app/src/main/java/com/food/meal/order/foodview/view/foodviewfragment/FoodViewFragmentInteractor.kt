package com.food.meal.order.foodview.view.foodviewfragment

import android.content.res.loader.ResourcesProvider
import android.util.Log
import android.widget.Toast
import com.food.meal.order.foodview.model.data.AppState
import com.food.meal.order.foodview.repo.cache.room.FoodDatabase
import com.food.meal.order.foodview.repo.cache.room.FoodEntity
import com.food.meal.order.foodview.repo.retrofit.FoodRetrofit
import com.food.meal.order.foodview.repo.retrofit.FoodRetrofitImpl
import com.food.meal.order.foodview.repo.retrofit.OnFoodListLoadedToDbListener
import com.food.meal.order.foodview.utils.LOG_TAG
import com.food.meal.order.foodview.utils.getKindFood
import com.food.meal.order.foodview.utils.network.NetworkStatus
import com.food.meal.order.foodview.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent
import java.util.concurrent.CopyOnWriteArrayList

class FoodViewFragmentInteractor(
    private val networkStatus: NetworkStatus,
    private val db: FoodDatabase,
    private val onReadyDataToShow: OnReadyDataToShow
) {
    /** Исходные данные */ //region
    val foodRetrofit: FoodRetrofit =
        FoodRetrofitImpl(KoinJavaComponent.getKoin().get(), KoinJavaComponent.getKoin().get(),
            object: OnFoodListLoadedToDbListener {
                override fun loadFoodListFromDb(
                    kindFoodIndex: Int, foodList: CopyOnWriteArrayList<FoodEntity>) {
//                    onReadyDataToShow.getFromDb(
//                        AppState.Success(db.foodDao().getByKindFoodIndex(kindFoodIndex)))
                    onReadyDataToShow.getFromDb(
                        AppState.Success(foodList))
                }
            })
    //endregion

    fun getListFood(selectedKindFoodIndex: Int) {
        if (networkStatus.isOnline())
            foodRetrofit.getFood(getKindFood(selectedKindFoodIndex))
        else {}
    }
}