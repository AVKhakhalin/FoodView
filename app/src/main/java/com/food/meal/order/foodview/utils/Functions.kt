package com.food.meal.order.foodview.utils

fun getKindFoodIndex(kindFood: String): Int {
    KIND_FOOD_LIST_ENG.forEachIndexed { index, curKindFood ->
        if (kindFood == curKindFood) return index
    }
    KIND_FOOD_LIST_RUS.forEachIndexed { index, curKindFood ->
        if (kindFood == curKindFood) return index
    }
    // Индекс ошибки, когда индекс вида еды определить не удалось
    return -1
}

// Генератор цены еды
fun getFoodPrice(): Int {
    return 300 + ((Math.random() - 0.5) * 50).toInt()
}