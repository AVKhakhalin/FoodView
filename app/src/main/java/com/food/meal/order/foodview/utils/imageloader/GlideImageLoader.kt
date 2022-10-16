package com.food.meal.order.foodview.utils.imageloader

interface GlideImageLoader<T> {
    fun loadInto(url: String, container: T)
}