package com.food.meal.order.foodview.view.foodviewfragment

import com.food.meal.order.foodview.model.data.AppState

interface OnReadyDataToShow {
    fun getFromDb(appState: AppState)
}