package com.food.meal.order.foodview.repo.cache

import com.food.meal.order.foodview.repo.cache.room.FoodDao

class RepoCacheImpl(
    private val dbDao: FoodDao
): RepoCache {
}