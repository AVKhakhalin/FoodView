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
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger

class FoodRetrofitImpl(
    private val retrofitService: RetrofitService,
    private val db: FoodDatabase,
    private val onFoodListLoadedToDbListener: OnFoodListLoadedToDbListener
): FoodRetrofit {
    override fun getFood(kindFood: String) {
        // Класс для сохранения результата
        val foodList: CopyOnWriteArrayList<FoodEntity> = CopyOnWriteArrayList<FoodEntity>()
        val kindFoodIndex: Int = getKindFoodIndex(kindFood)
        // Получение нового списка еды определённого вида
        retrofitService.getFood(kindFood)
            .subscribeOn(Schedulers.single())
            .observeOn(Schedulers.io())
            .subscribe({ foodModel ->
                // Удаление старых данных о текущем виде еды из базы данных
                db.foodDao().deleteByKindFoodIndex(kindFoodIndex)
                // Задание счётчика для анализа пройдённых потоков
                val numberElaboratedData: AtomicInteger = AtomicInteger(0)
                repeat(foodModel.hints.size) { index ->
                    foodModel.hints[index].food?.let { food ->
                        food.label?.let {
                            retrofitService.getIngredients(it)
                                .subscribeOn(Schedulers.single())
                                .observeOn(Schedulers.io())
                                .subscribe({ ingredientsFoodModel ->
                                    // Добавление новых данных о виде еды в базу данных
                                    if (ingredientsFoodModel.hits.size > 0) {
                                        ingredientsFoodModel.hits[0].recipe?.let { recipe ->
                                            foodList.add(
                                                FoodEntity(
                                                    kindFoodIndex,
                                                    food.label ?: "",
                                                    getDescriptionIngredients(recipe.ingredients),
                                                    food.image ?: "",
                                                    getFoodPrice()
                                                )
                                            )
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
                                    if ((foodList.size > 5) ||
                                        (numberElaboratedData.incrementAndGet() ==
                                        foodModel.hints.size)) {
                                        onFoodListLoadedToDbListener.
                                            loadFoodListFromDb(kindFoodIndex, foodList)
                                    }
                                }) {
                                    // Действия при ошибке в запросе ингредиентов для еды
                                    Log.d(LOG_TAG, "ОШИБКА: получения ингредиентов для еды")
                                    if ((foodList.size > 5) ||
                                        (numberElaboratedData.incrementAndGet() ==
                                        foodModel.hints.size)) {
                                        onFoodListLoadedToDbListener.
                                            loadFoodListFromDb(kindFoodIndex, foodList)
                                    }
                                }
                        }
                    }
                }
            }) {
                // Действия при ошибке в запросе разных видов еды
                Log.d(LOG_TAG, "ОШИБКА: получение списка видов еды")
            }
    }
}