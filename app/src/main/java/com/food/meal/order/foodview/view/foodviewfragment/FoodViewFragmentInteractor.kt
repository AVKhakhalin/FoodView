package com.food.meal.order.foodview.view.foodviewfragment

import com.food.meal.order.foodview.repo.retrofit.FoodRetrofit
import com.food.meal.order.foodview.repo.retrofit.FoodRetrofitImpl
import org.koin.java.KoinJavaComponent

class FoodViewFragmentInteractor {
    val foodRetrofit: FoodRetrofit = FoodRetrofitImpl(KoinJavaComponent.getKoin().get())
}