package com.food.meal.order.foodview.view.foodadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.R

class FoodListRecyclerAdapter(private val foodList: List<String>):
    RecyclerView.Adapter<FoodListRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.food_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.food_list_recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Установка названия еды
        holder.foodName.text = foodList[position]
    }

    override fun getItemCount() = foodList.size
}