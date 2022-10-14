package com.food.meal.order.foodview.view.kindfoodadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.R

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
        // Установка названия еды
        holder.kindFoodName.text = kindFoodList[position]
    }

    override fun getItemCount() = kindFoodList.size
}