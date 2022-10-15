package com.food.meal.order.foodview.view.foodviewfragment

import android.content.Context
import com.food.meal.order.foodview.databinding.FragmentFoodViewBinding
import com.food.meal.order.foodview.model.base.BaseFragment
import com.food.meal.order.foodview.utils.FOOD_VIEW_FRAGMENT_SCOPE
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class FoodViewFragment:
    BaseFragment<FragmentFoodViewBinding>(FragmentFoodViewBinding::inflate) {
    //** Исходные данные */ //region
    // ViewModel
    private lateinit var viewModel: FoodViewFragmentViewModel
    // CreateUserFragmentScope
    private lateinit var showFoodViewFragmentScope: Scope
    // newInstance для данного класса
    companion object {
        fun newInstance(): FoodViewFragment = FoodViewFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showFoodViewFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FOOD_VIEW_FRAGMENT_SCOPE, named(FOOD_VIEW_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showFoodViewFragmentScope.close()
        super.onDetach()
    }
    //endregion
}