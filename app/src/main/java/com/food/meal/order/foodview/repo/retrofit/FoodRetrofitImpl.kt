package com.food.meal.order.foodview.repo.retrofit

import android.util.Log
import com.food.meal.order.foodview.utils.LOG_TAG
import com.food.meal.order.foodview.utils.network.RetrofitService
import io.reactivex.rxjava3.schedulers.Schedulers

class FoodRetrofitImpl(
    private val retrofitService: RetrofitService
): FoodRetrofit {
    override fun getFood(kindFood: String) {
        // Получение списка еды определённого вида
        retrofitService.getFood(kindFood)
            .subscribeOn(Schedulers.single())
            .observeOn(Schedulers.single())
            .subscribe({
                repeat(it.hints.size) { index ->
                    Log.d(LOG_TAG, "Название еды: ${it.hints[index].food?.label}")
                    Log.d(LOG_TAG, "Картинка: ${it.hints[index].food?.image}")
                    it.hints[index].food?.let { food ->
                        food.label?.let {
                            retrofitService.getIngredients(it)
                                .subscribeOn(Schedulers.single())
                                .observeOn(Schedulers.single())
                                .subscribe({
                                    Log.d(LOG_TAG, "Ингредиенты: ${
                                        it.hits[0].recipe?.ingredients?.size}")
                                }) {
                                    // Действия при ошибке в запросе ингредиентов для еды
                                    Log.d(LOG_TAG, "ОШИБКА получение ингрединетов для еды")
                                }
                        }
                    }
                }
            }) {
                // Действия при ошибке в запросе разных видов еды
                Log.d(LOG_TAG, "ОШИБКА получение списка видов еды")
            }
    }
}