package com.food.meal.order.foodview.repo.cache

import com.food.meal.order.foodview.repo.cache.room.FoodDao

class FoodCacheImpl(
    private val dbDao: FoodDao
): FoodCache {
}