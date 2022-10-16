package com.food.meal.order.foodview.view.foodviewfragment

import androidx.lifecycle.LiveData
import com.food.meal.order.foodview.model.base.BaseViewModel
import com.food.meal.order.foodview.model.data.AppState

class FoodViewFragmentViewModel(
    private val interactor: FoodViewFragmentInteractor
): BaseViewModel<AppState>() {
    /** Задание исходных данных */ //region
    // Информация с результатом запроса
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    //endregion

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }
}