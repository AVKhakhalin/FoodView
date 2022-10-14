package com.food.meal.order.foodview.view.kindfoodadapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.R
import com.food.meal.order.foodview.utils.FONT_ROBOTO
import com.food.meal.order.foodview.utils.FONT_SF_UI_DISPLAY

class KindFoodListRecyclerAdapter(private val kindFoodList: List<String>):
    RecyclerView.Adapter<KindFoodListRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val kindFoodName: TextView = itemView.findViewById(R.id.kind_food_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.kind_food_list_recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Установка вида еды
        holder.kindFoodName.text = kindFoodList[position]
        holder.kindFoodName.typeface =
            Typeface.createFromAsset(holder.itemView.context.assets, FONT_SF_UI_DISPLAY)
    }

    override fun getItemCount() = kindFoodList.size
}