package com.food.meal.order.foodview.repo.retrofit

import android.util.Log
import com.food.meal.order.foodview.repo.cache.room.FoodDatabase
import com.food.meal.order.foodview.repo.cache.room.FoodEntity
import com.food.meal.order.foodview.utils.LOG_TAG
import com.food.meal.order.foodview.utils.getDescriptionIngredients
import com.food.meal.order.foodview.utils.getFoodPrice
import com.food.meal.order.foodview.utils.getKindFoodIndex
import com.food.meal.order.foodview.utils.network.RetrofitService
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger

class FoodRetrofitImpl(
    private val retrofitService: RetrofitService,
    private val db: FoodDatabase
): FoodRetrofit {
    override fun getFood(kindFood: String) {
        val kindFoodIndex: Int = getKindFoodIndex(kindFood)
        // Удаление старых данных о текущем виде еды в базе данных
        db.foodDao().deleteByKindFoodIndex(kindFoodIndex)
        // Получение нового списка еды определённого вида
        retrofitService.getFood(kindFood)
            .subscribeOn(Schedulers.single())
            .observeOn(Schedulers.single())
            .subscribe({ foodModel ->
                val numberElaboratedData: AtomicInteger = AtomicInteger(0)
                repeat(foodModel.hints.size) { index ->
                    foodModel.hints[index].food?.let { food ->
                        food.label?.let {
                            retrofitService.getIngredients(it)
                                .subscribeOn(Schedulers.single())
                                .observeOn(Schedulers.single())
                                .subscribe({ ingredientsFoodModel ->
                                    // Добавление новых данных о виде еды в базу данных
                                    if (ingredientsFoodModel.hits.size > 0) {
                                        ingredientsFoodModel.hits[0].recipe?.let { recipe ->
                                            db.foodDao().insert(
                                                FoodEntity(
                                                    kindFoodIndex,
                                                    food.label ?: "",
                                                    getDescriptionIngredients(recipe.ingredients),
                                                    food.image ?: "",
                                                    getFoodPrice()
                                                )
                                            )
                                        }
                                    }
                                    // Действия при обработке данных
                                    if (numberElaboratedData.incrementAndGet() ==
                                        foodModel.hints.size) {
                                        // TODO: запустить изменение LiveData
                                    }
                                }) {
                                    // Действия при ошибке в запросе ингредиентов для еды
                                    Log.d(LOG_TAG, "ОШИБКА получения ингрединетов для еды")
                                    // Действия при обработке данных
                                    if (numberElaboratedData.incrementAndGet() ==
                                        foodModel.hints.size) {
                                        // TODO: запустить изменение LiveData
                                    }
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