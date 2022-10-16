package com.food.meal.order.foodview.view.foodviewfragment

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.databinding.FragmentFoodViewBinding
import com.food.meal.order.foodview.model.base.BaseFragment
import com.food.meal.order.foodview.model.data.AppState
import com.food.meal.order.foodview.repo.retrofit.FoodRetrofit
import com.food.meal.order.foodview.repo.retrofit.FoodRetrofitImpl
import com.food.meal.order.foodview.utils.FONT_INTER
import com.food.meal.order.foodview.utils.FONT_ROBOTO
import com.food.meal.order.foodview.utils.FOOD_VIEW_FRAGMENT_SCOPE
import com.food.meal.order.foodview.utils.LOG_TAG
import com.food.meal.order.foodview.view.foodviewfragment.adapters.FoodListRecyclerAdapter
import com.food.meal.order.foodview.view.foodviewfragment.adapters.KindFoodListRecyclerAdapter
import org.koin.core.Koin
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

    // Временные данные для проверки работоспособности макета
    val kindFoodList: List<String> = listOf("Пицца", "Комбо", "Десерты", "Напитки")
    // Горизонтальный список видов еды
    lateinit var kindFoodListRecyclerView: RecyclerView
    // Вертикальный список еды
    lateinit var foodListRecyclerView: RecyclerView
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Установка списка видов еды
        kindFoodListRecyclerView = binding.kindFoodList
        kindFoodListRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false)
        kindFoodListRecyclerView.adapter = KindFoodListRecyclerAdapter(kindFoodList)
        // Установка списка еды
        foodListRecyclerView = binding.foodList
        foodListRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false)
//        foodListRecyclerView.adapter = FoodListRecyclerAdapter(foodList)

        // Установка шрифтов элементам макета
        setFontsToElements()

        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: FoodViewFragmentViewModel by showFoodViewFragmentScope.inject()
        viewModel = _viewModel
        // Подписка на ViewModel
        this.viewModel.subscribe().observe(viewLifecycleOwner) { renderData(it) }
    }

    // Отображение информации о еде
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                // Изменение внешнего вида фрагмента
                foodListRecyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
                // Установка списка еды
                appState.foodList?.let {
                    foodListRecyclerView.adapter = FoodListRecyclerAdapter(it)
                }
            }
            is AppState.Loading -> {
                // Изменение внешнего вида фрагмента
                foodListRecyclerView.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                // Изменение внешнего вида фрагмента
                foodListRecyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
                // Уведомление пользователя об ошибке
                Toast.makeText(requireActivity(), appState.error.message, Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }

    // Установка шрифтов элементам
    private fun setFontsToElements() {
        binding.cityTitle.typeface = Typeface.createFromAsset(requireContext().assets, FONT_ROBOTO)
        binding.menuTitle.typeface = Typeface.createFromAsset(requireContext().assets, FONT_INTER)
        binding.profileTitle.typeface =
            Typeface.createFromAsset(requireContext().assets, FONT_INTER)
        binding.basketTitle.typeface = Typeface.createFromAsset(requireContext().assets, FONT_INTER)
    }
}