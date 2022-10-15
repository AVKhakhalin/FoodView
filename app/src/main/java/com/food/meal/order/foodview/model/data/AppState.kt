package com.food.meal.order.foodview.model.data

sealed class AppState {
    data class Success(val data: String?): AppState() // TODO: Исправить тип String на конечный класс
    data class Error(val error: Throwable): AppState()
    data class Loading(val progress: Int?): AppState()
}