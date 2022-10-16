package com.food.meal.order.foodview.view.foodviewfragment.adapters

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.food.meal.order.foodview.R
import com.food.meal.order.foodview.repo.cache.room.FoodEntity
import com.food.meal.order.foodview.utils.FONT_ROBOTO
import com.food.meal.order.foodview.utils.FONT_SF_UI_DISPLAY
import com.food.meal.order.foodview.utils.imageloader.GlideImageLoaderImpl
import org.koin.java.KoinJavaComponent

class FoodListRecyclerAdapter(private val foodList: List<FoodEntity>):
    RecyclerView.Adapter<FoodListRecyclerAdapter.MyViewHolder>() {
    /** Исходные данные */ //region
    // GlideImageLoaderImpl
    private val glideImageLoaderImpl: GlideImageLoaderImpl = KoinJavaComponent.getKoin().get()
    //endregion

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val foodImage: AppCompatImageView = itemView.findViewById(R.id.food_image)
        val foodName: TextView = itemView.findViewById(R.id.food_name)
        val foodIngredients: TextView = itemView.findViewById(R.id.food_ingredients)
        val foodPrice: AppCompatButton = itemView.findViewById(R.id.food_price_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).
        inflate(R.layout.food_list_recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Установка картинки еды
        val imageLink: String = foodList[position].imageLink
        glideImageLoaderImpl.loadInto(imageLink, holder.foodImage)
        // Установка названия еды
        holder.foodName.text = foodList[position].name
        holder.foodName.typeface =
            Typeface.createFromAsset(holder.itemView.context.assets, FONT_ROBOTO)
        // Установка ингредиентов еды
        holder.foodIngredients.text = foodList[position].ingredients
        holder.foodIngredients.typeface =
            Typeface.createFromAsset(holder.itemView.context.assets, FONT_SF_UI_DISPLAY)
        // Установка цены еды
        holder.foodPrice.text = "от ${foodList[position].price} р"
        holder.foodPrice.typeface =
            Typeface.createFromAsset(holder.itemView.context.assets, FONT_SF_UI_DISPLAY)
    }

    override fun getItemCount() = foodList.size
}