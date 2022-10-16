package com.food.meal.order.foodview.view.foodviewfragment

import android.util.Log
import androidx.lifecycle.LiveData
import com.food.meal.order.foodview.model.base.BaseViewModel
import com.food.meal.order.foodview.model.data.AppState
import com.food.meal.order.foodview.utils.LOG_TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent

class FoodViewFragmentViewModel: BaseViewModel<AppState>() {
    /** Задание исходных данных */ //region
    // Интерактор
    private val interactor: FoodViewFragmentInteractor = FoodViewFragmentInteractor(
        KoinJavaComponent.getKoin().get(), KoinJavaComponent.getKoin().get(),
            object: OnReadyDataToShow {
                override fun getFromDb(appState: AppState) {
                    _mutableLiveData.postValue(appState)
                    Log.d(LOG_TAG,"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ${appState}")
                }
            })
    // Информация с результатом запроса
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    //endregion

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    // Получение списка еды
    fun getListFood(selectedKindFoodIndex: Int) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            withContext(Dispatchers.IO) {
                interactor.getListFood(selectedKindFoodIndex)
            }
        }
    }
}