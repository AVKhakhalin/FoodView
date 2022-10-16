package com.food.meal.order.foodview.repo.retrofit

import com.food.meal.order.foodview.repo.cache.room.FoodEntity
import java.util.concurrent.CopyOnWriteArrayList

interface OnFoodListLoadedToDbListener {
    fun loadFoodListFromDb(kindFoodIndex: Int, foodList: CopyOnWriteArrayList<FoodEntity>)
}