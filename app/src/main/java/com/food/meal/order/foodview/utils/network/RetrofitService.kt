package com.food.meal.order.foodview.utils.network

import com.food.meal.order.foodview.BuildConfig
import com.food.meal.order.foodview.model.data.FoodModel
import com.food.meal.order.foodview.model.data.IngredientsFoodModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RetrofitService {
    // KINDS FOOD -------------------------------------------------
    @Headers("Accept: application/json")
    @GET("/api/food-database/v2/parser?app_id=${
        BuildConfig.FOOD_API_ID}&app_key=${BuildConfig.FOOD_API_KEY
        }&nutrition-type=cooking&health=alcohol-free&category=generic-foods")
    fun getFood(@Query("ingr") kindFood: String): Single<FoodModel>

    // FOOD INGREDIENTS -------------------------------------------------
    @Headers("Accept: application/json")
    @GET("/api/recipes/v2?type=public&app_id=${
        BuildConfig.INGREDIENTS_API_ID}&app_key=${BuildConfig.INGREDIENTS_API_KEY}")
    fun getIngredients(@Query("q") foodName: String): Single<IngredientsFoodModel>
}