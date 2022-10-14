package com.food.meal.order.foodview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.databinding.ActivityMainBinding
import com.food.meal.order.foodview.view.foodadapter.FoodListRecyclerAdapter
import com.food.meal.order.foodview.view.kindfoodadapter.KindFoodListRecyclerAdapter

class MainActivity: AppCompatActivity() {
    /** Исходные данные */ //region
    // Binding
    private lateinit var binding: ActivityMainBinding
    // Временные данные для проверки работоспособности макета
    val kindFoodList: List<String> = listOf("Пицца", "Комбо", "Десерты", "Напитки",
        "Пицца", "Комбо", "Десерты", "Напитки", "Пицца", "Комбо", "Десерты", "Напитки")
    val foodList: List<String> = listOf("Пицца", "Изюм", "Мандарины", "Баклажаны", "Пицца", "Изюм",
        "Мандарины", "Баклажаны", "Пицца", "Изюм", "Мандарины", "Баклажаны", "Пицца", "Изюм",
        "Мандарины", "Баклажаны", "Пицца", "Изюм", "Мандарины", "Баклажаны", "Пицца", "Изюм",
        "Мандарины", "Баклажаны")
    // Горизонтальный список видов еды
    lateinit var kindFoodListRecyclerView: RecyclerView
    // Вертикальный список еды
    lateinit var foodListRecyclerView: RecyclerView
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Подключение Binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Установка списка видов еды
        kindFoodListRecyclerView = binding.kindFoodList
        kindFoodListRecyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)
        kindFoodListRecyclerView.adapter = KindFoodListRecyclerAdapter(kindFoodList)
        // Установка списка еды
        foodListRecyclerView = binding.foodList
        foodListRecyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        foodListRecyclerView.adapter = FoodListRecyclerAdapter(foodList)

        // Отображение содержимого окна
        setContentView(binding.root)
    }
}