package com.food.meal.order.foodview.utils.resources

import android.content.Context

class ResourcesProviderImpl(
    private val context: Context
): ResourcesProvider {
    override fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun getContext(): Context {
        return context
    }
}