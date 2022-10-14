package com.food.meal.order.foodview.view

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.databinding.ActivityMainBinding
import com.food.meal.order.foodview.utils.FONT_INTER
import com.food.meal.order.foodview.utils.FONT_ROBOTO
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

        Toast.makeText(this, "${foodList.size}", Toast.LENGTH_SHORT).show()

        // Установка шрифтов элементам макета
        setFontsToElements()

        // Отображение содержимого окна
        setContentView(binding.root)
    }

    // Установка шрифтов элементам
    private fun setFontsToElements() {
        binding.cityTitle.typeface = Typeface.createFromAsset(assets, FONT_ROBOTO)
        binding.menuTitle.typeface = Typeface.createFromAsset(assets, FONT_INTER)
        binding.profileTitle.typeface = Typeface.createFromAsset(assets, FONT_INTER)
        binding.basketTitle.typeface = Typeface.createFromAsset(assets, FONT_INTER)
    }

}