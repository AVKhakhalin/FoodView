package com.food.meal.order.foodview.app

import android.app.Application
import com.food.meal.order.foodview.di.application
import com.food.meal.order.foodview.di.database
import com.food.meal.order.foodview.di.screens
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    /** Исходные данные */ //region
    companion object {
        lateinit var instance: App
    }
    // endregion

    override fun onCreate() {
        super.onCreate()
        // Инициализация класса App
        instance = this

        // Koin
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, screens, database))
        }
    }
}