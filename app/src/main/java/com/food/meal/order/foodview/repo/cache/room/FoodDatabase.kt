package com.food.meal.order.foodview.repo.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    FoodEntity::class], version = 1)

abstract class FoodDatabase: RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        private var instance: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDatabase::class.java, "foodDatabase.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance!!
        }
    }
}