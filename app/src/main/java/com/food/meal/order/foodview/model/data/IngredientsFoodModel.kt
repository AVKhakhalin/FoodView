package com.food.meal.order.foodview.model.data

import com.google.gson.annotations.SerializedName

data class IngredientsFoodModel(
    @SerializedName("from")
    var from: Int? = null,
    @SerializedName("to")
    var to: Int? = null,
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("hits")
    var hits: ArrayList<Hits> = arrayListOf()
)

data class Hits (
    @SerializedName("recipe")
    var recipe: Recipe? = Recipe(),
)

data class Recipe (
    @SerializedName("ingredients")
    var ingredients: ArrayList<Ingredients> = arrayListOf()
)

data class Ingredients (
    @SerializedName("food")
    var food: String? = null // Название ингредиента
)