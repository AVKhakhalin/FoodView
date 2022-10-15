package com.food.meal.order.foodview.view.activity

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.R
import com.food.meal.order.foodview.databinding.ActivityMainBinding
import com.food.meal.order.foodview.navigator.BackButtonListener
import com.food.meal.order.foodview.utils.FONT_INTER
import com.food.meal.order.foodview.utils.FONT_ROBOTO
import com.food.meal.order.foodview.utils.MAIN_ACTIVITY_SCOPE
import com.food.meal.order.foodview.view.foodviewfragment.adapters.FoodListRecyclerAdapter
import com.food.meal.order.foodview.view.foodviewfragment.adapters.KindFoodListRecyclerAdapter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class MainActivity: AppCompatActivity(), FragmentManager.OnBackStackChangedListener {
    /** Исходные данные */ //region
    // Binding
    private lateinit var binding: ActivityMainBinding
    // MainActivityScope
    private val mainActivityScope: Scope = KoinJavaComponent.getKoin().getOrCreateScope(
        MAIN_ACTIVITY_SCOPE, named(MAIN_ACTIVITY_SCOPE)
    )
    // ViewModel
    private lateinit var viewModel: MainActivityViewModel
    // Навигация
    private val navigator = AppNavigator(this@MainActivity, R.id.fragments_container)
    private val navigatorHolder: NavigatorHolder = KoinJavaComponent.getKoin().get()

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

    override fun onDestroy() {
        // Удаление скоупа для активити
        mainActivityScope.close()
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Подключение Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Инициализация ViewModel
        initViewModel()
        // Отслеживание первого или последующего запусков MainActivity
        if (savedInstanceState != null) {
            // Установка текущего экрана приложения
            navigatorHolder.setNavigator(navigator)
        }

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

    // Инициализация ViewModel
    private fun initViewModel() {
        // Начальная установка ViewModel
        val viewModel: MainActivityViewModel by mainActivityScope.inject()
        this.viewModel = viewModel
    }

    /** Методы для настройки навигатора */ //region
    override fun onResumeFragments() {
        super.onResumeFragments()
        // Установка навигатора
        navigatorHolder.setNavigator(navigator)
    }
    override fun onPause() {
        // Удаление навигатора
        navigatorHolder.removeNavigator()
        super.onPause()
    }
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        viewModel.router.exit()
    }
    override fun onBackStackChanged() {
        // Закрытие приложения, когда не открыт ни один фрагмент
        if (supportFragmentManager.backStackEntryCount == 0) finish()
    }
    //endregion
}