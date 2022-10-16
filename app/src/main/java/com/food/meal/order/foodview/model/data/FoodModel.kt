package com.food.meal.order.foodview.model.data

import com.google.gson.annotations.SerializedName


data class FoodModel(
    @SerializedName("text")
    var text: String? = null,
    @SerializedName("hints")
    var hints: ArrayList<Hints> = arrayListOf(),
)

data class Hints (
    @SerializedName("food")
    var food: Food? = Food()
)

data class Food (
    @SerializedName("foodId")
    var foodId: String? = null,
    @SerializedName("label")
    var label: String? = null, // Название продукта
    @SerializedName("knownAs")
    var knownAs: String? = null,
    @SerializedName("image")
    var image: String? = null // Ссылка на картинку
)


