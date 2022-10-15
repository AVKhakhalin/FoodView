package com.food.meal.order.foodview.di

import com.food.meal.order.foodview.utils.network.NetworkStatus
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val application = module {

    // Вспомогательные классы:
    // Определение статуса сети
    single<NetworkStatus> { NetworkStatus(androidContext()) }
}
