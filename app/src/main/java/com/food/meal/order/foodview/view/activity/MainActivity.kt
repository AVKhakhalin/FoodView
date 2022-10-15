package com.food.meal.order.foodview.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.food.meal.order.foodview.R
import com.food.meal.order.foodview.databinding.ActivityMainBinding
import com.food.meal.order.foodview.navigator.BackButtonListener
import com.food.meal.order.foodview.utils.MAIN_ACTIVITY_SCOPE
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
        // Инициализация кнопок для проверки тестового задания
        initCheckButtons()
        // Отображение содержимого окна
        setContentView(binding.root)
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
        // Отображение текста с тестовым заданием:
        binding.taskDescriptionScroll.visibility = View.VISIBLE
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        // Закрыите приложения
        viewModel.router.exit()
    }
    override fun onBackStackChanged() {
        // Закрытие приложения, когда не открыт ни один фрагмент
        if (supportFragmentManager.backStackEntryCount == 0) finish()
    }
    //endregion

    // Инициализация кнопок для проверки тестового задания
    private fun initCheckButtons() {
        binding.moveToFoodViewFragmentButtonTop.setOnClickListener {
            viewModel.router.navigateTo(viewModel.screens.foodViewScreen())
            binding.taskDescriptionScroll.visibility = View.GONE
        }
        binding.moveToFoodViewFragmentButtonBottom.setOnClickListener {
            viewModel.router.navigateTo(viewModel.screens.foodViewScreen())
            binding.taskDescriptionScroll.visibility = View.GONE
        }
    }
}