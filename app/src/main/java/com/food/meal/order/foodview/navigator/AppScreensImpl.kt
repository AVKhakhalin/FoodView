package com.food.meal.order.foodview.navigator

import com.food.meal.order.foodview.view.foodviewfragment.FoodViewFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AppScreensImpl: AppScreens {
    // Окно с начальными кнопками выбора пользователя
    override fun foodViewScreen() = FragmentScreen {
        FoodViewFragment.newInstance()
    }
}