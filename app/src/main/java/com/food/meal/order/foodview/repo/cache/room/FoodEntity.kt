package com.food.meal.order.foodview.repo.cache.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foodEntity")
data class FoodEntity(
    val kindIndex: Int,
    val name: String,
    val ingredients: String,
    val imageLink: String,
    val price: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}