package com.food.meal.order.foodview.view.foodviewfragment

import com.food.meal.order.foodview.model.base.BaseViewModel
import com.food.meal.order.foodview.model.data.AppState

class FoodViewFragmentViewModel(
    private val interactor: FoodViewFragmentInteractor,
): BaseViewModel<AppState>() {

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }
}