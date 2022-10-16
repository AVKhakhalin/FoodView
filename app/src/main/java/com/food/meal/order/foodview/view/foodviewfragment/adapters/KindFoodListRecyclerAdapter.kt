package com.food.meal.order.foodview.view.foodviewfragment.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.R
import com.food.meal.order.foodview.utils.FONT_SF_UI_DISPLAY

class KindFoodListRecyclerAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private val selectedKindFoodIndex: Int,
    private val kindFoodList: List<String>
): RecyclerView.Adapter<KindFoodListRecyclerAdapter.MyViewHolder>() {
    /** Исходные данные */ //region
    // View, по которой кликнули в предыдущий раз
    val oldViewSelect: MutableList<View> = mutableListOf()
    val oldViewNotSelect: MutableList<View> = mutableListOf()
    var oldPosition: Int = selectedKindFoodIndex
    //endregion

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val kindFoodNameSelected: AppCompatButton =
            itemView.findViewById(R.id.kind_food_button_selected)
        val kindFoodNameNotSelected: AppCompatButton =
            itemView.findViewById(R.id.kind_food_button_not_selected)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.kind_food_list_recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Установка вида еды
        if (selectedKindFoodIndex == position) {
            holder.kindFoodNameSelected.visibility = View.VISIBLE
            holder.kindFoodNameNotSelected.visibility = View.GONE
        } else {
            holder.kindFoodNameSelected.visibility = View.GONE
            holder.kindFoodNameNotSelected.visibility = View.VISIBLE
        }
        // Установка шрифта и текста на кнопку для отображения выделения
        holder.kindFoodNameSelected.typeface =
            Typeface.createFromAsset(holder.itemView.context.assets, FONT_SF_UI_DISPLAY)
        holder.kindFoodNameSelected.text = kindFoodList[position]
        holder.kindFoodNameSelected.setOnClickListener {
            onListItemClickListener.onItemClick(position)
            // Обработка прежних выделенных элементов
            if (oldPosition != position) {
                oldViewSelect[position].visibility = View.VISIBLE
                oldViewNotSelect[position].visibility = View.GONE
                oldViewSelect[oldPosition].visibility = View.GONE
                oldViewNotSelect[oldPosition].visibility = View.VISIBLE
                oldPosition = position
            }
        }

        // Установка шрифта и текста на кнопку для отображения отсутствия выделения
        holder.kindFoodNameNotSelected.typeface =
            Typeface.createFromAsset(holder.itemView.context.assets, FONT_SF_UI_DISPLAY)
        holder.kindFoodNameNotSelected.text = kindFoodList[position]
        holder.kindFoodNameNotSelected.setOnClickListener {
            onListItemClickListener.onItemClick(position)
            // Обработка прежних выделенных элементов
            if (oldPosition != position) {
                oldViewSelect[position].visibility = View.VISIBLE
                oldViewNotSelect[position].visibility = View.GONE
                oldViewSelect[oldPosition].visibility = View.GONE
                oldViewNotSelect[oldPosition].visibility = View.VISIBLE
                oldPosition = position
            }
        }

        // Сохранение элементов вью
        oldViewSelect.add(holder.kindFoodNameSelected)
        oldViewNotSelect.add(holder.kindFoodNameNotSelected)
    }

    override fun getItemCount() = kindFoodList.size
}