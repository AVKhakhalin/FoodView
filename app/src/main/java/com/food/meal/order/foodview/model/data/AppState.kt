package com.food.meal.order.foodview.model.data

import com.food.meal.order.foodview.repo.cache.room.FoodEntity

sealed class AppState {
    data class Success(val foodList: List<FoodEntity>?): AppState()
    data class Error(val error: Throwable): AppState()
    data class Loading(val progress: Int?): AppState()
}