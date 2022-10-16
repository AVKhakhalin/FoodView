package com.food.meal.order.foodview.utils

import com.food.meal.order.foodview.model.data.Ingredients

fun getKindFoodIndex(kindFood: String): Int {
    KIND_FOOD_LIST_ENG.forEachIndexed { index, curKindFood ->
        if (kindFood.lowercase() == curKindFood.lowercase()) return index
    }
    KIND_FOOD_LIST_RUS.forEachIndexed { index, curKindFood ->
        if (kindFood.lowercase() == curKindFood.lowercase()) return index
    }
    // Индекс ошибки, когда индекс вида еды определить не удалось
    return -1
}

// Генератор цены еды
fun getFoodPrice(): Int {
    return 300 + ((Math.random() - 0.5) * 50).toInt()
}

// Получение из списка индгредиентов их полное описание
fun getDescriptionIngredients(ingredients: ArrayList<Ingredients>?): String {
    return if (ingredients == null)
        ""
    else {
        val sizeIngredients: Int = ingredients.size
        var description: String = ""
        ingredients.forEachIndexed { index, ingredient ->
            ingredient.food?.let { food ->
                description += if ((index == 0) && (sizeIngredients == 1)) {
                                    food[0].uppercase() + food.replace(")", "").
                                        subSequence(1, food.length)
                               }
                               else if ((index == 0) && (index <= sizeIngredients - 2))
                                    food[0].uppercase() + food.replace(")", "").
                                        lowercase().subSequence(1, food.length) + ", "
                               else if (index <= sizeIngredients - 2)
                                    food.replace(")", "").lowercase() + ", "
                               else
                                    food.replace(")", "").lowercase()
            }
        }
        description
    }
}