package com.food.meal.order.foodview.di

import android.widget.ImageView
import com.food.meal.order.foodview.navigator.AppScreens
import com.food.meal.order.foodview.navigator.AppScreensImpl
import com.food.meal.order.foodview.repo.cache.room.FoodDatabase
import com.food.meal.order.foodview.utils.CICERONE_NAME
import com.food.meal.order.foodview.utils.FOOD_VIEW_FRAGMENT_SCOPE
import com.food.meal.order.foodview.utils.MAIN_ACTIVITY_SCOPE
import com.food.meal.order.foodview.utils.imageloader.GlideImageLoaderImpl
import com.food.meal.order.foodview.utils.network.*
import com.food.meal.order.foodview.utils.resources.ResourcesProvider
import com.food.meal.order.foodview.utils.resources.ResourcesProviderImpl
import com.food.meal.order.foodview.view.activity.MainActivityViewModel
import com.food.meal.order.foodview.view.foodviewfragment.FoodViewFragmentViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {

    // Навигация между окнами
    single<Cicerone<Router>>(named(CICERONE_NAME)) { Cicerone.create() }
    single<NavigatorHolder> {
        get<Cicerone<Router>>(named(CICERONE_NAME)).getNavigatorHolder() }
    single<Router> { get<Cicerone<Router>>(named(CICERONE_NAME)).router }
    single<AppScreens> { AppScreensImpl() }

    // Вспомогательные классы:
    // Определение статуса сети
    single<NetworkStatus> { NetworkStatus(androidContext()) }
    // Получение доступа к ресурсам
    single<ResourcesProvider> { ResourcesProviderImpl(androidContext()) }
    // Загрузка картинок
    single<GlideImageLoaderImpl> { GlideImageLoaderImpl() }
}

val screens = module {
    // Scope для MainActivity
    scope(named(MAIN_ACTIVITY_SCOPE)) {
        viewModel {
            MainActivityViewModel()
        }
    }

    // Scope для фрагмента с информацией о еде
    scope(named(FOOD_VIEW_FRAGMENT_SCOPE)) {
        viewModel {
            FoodViewFragmentViewModel()
        }
    }
}

val database = module {
    single { FoodDatabase.getInstance(get()) }
    single { get<FoodDatabase>().foodDao() }
}

val retrofit = module {
    factory { BaseInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
}
