package com.food.meal.order.foodview.view.foodviewfragment.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.R
import com.food.meal.order.foodview.utils.FONT_ROBOTO
import com.food.meal.order.foodview.utils.FONT_SF_UI_DISPLAY

class FoodListRecyclerAdapter(private val foodList: List<String>):
    RecyclerView.Adapter<FoodListRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.food_name)
        val foodIngredients: TextView = itemView.findViewById(R.id.food_ingredients)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.food_list_recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Установка названия еды
        holder.foodName.text = foodList[position]
        holder.foodName.typeface =
            Typeface.createFromAsset(holder.itemView.context.assets, FONT_ROBOTO)
        // Установка ингредиентов еды
        holder.foodIngredients.typeface =
            Typeface.createFromAsset(holder.itemView.context.assets, FONT_SF_UI_DISPLAY)

    }

    override fun getItemCount() = foodList.size
}