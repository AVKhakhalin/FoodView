package com.food.meal.order.foodview.utils

const val BASE_API_KIND_FOOD_URL: String = "https://api.edamam.com/api/recipes/v2"
const val BASE_API_INGREDIENTS_FOOD_URL: String = "https://api.edamam.com/api/food-database/v2/parser"
const val FONT_ROBOTO: String = "font/roboto_flex_regular.ttf"
const val FONT_INTER: String = "font/inter_light.otf"
const val FONT_SF_UI_DISPLAY: String = "font/sf_ui_display_light.otf"
const val MAIN_ACTIVITY_SCOPE: String = "MAIN_ACTIVITY_SCOPE"
const val FOOD_VIEW_FRAGMENT_SCOPE: String = "FOOD_VIEW_FRAGMENT_SCOPE"
const val CICERONE_NAME: String = "cicerone"
val KIND_FOOD_LIST_RUS: List<String> = listOf("Пицца", "Комбо", "Десерты", "Напитки")
val KIND_FOOD_LIST_ENG: List<String> = listOf("Pizza", "Combo meal", "Dessert", "Drink")
const val LOG_TAG: String = "mylogs"
// Типы сообщений при обмене сообщениями с сервером
enum class ServerResponseStatusCode {
    INFO,
    SUCCESS,
    REDIRECTION,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNDEFINED_ERROR
}