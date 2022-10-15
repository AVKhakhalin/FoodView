package com.food.meal.order.foodview.repo.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: FoodEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commit: List<FoodEntity>): Completable

    @Query("DELETE FROM FoodEntity WHERE kindIndex = :kindFoodIndex")
    fun deleteByKindFoodIndex(kindFoodIndex: Int): Completable

    @Query("SELECT * FROM FoodEntity WHERE kindIndex = :kindFoodIndex")
    fun getByKindFoodIndex(kindFoodIndex: Int): Single<List<FoodEntity>>
}