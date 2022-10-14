package com.food.meal.order.foodview

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.inter_display_text).typeface = Typeface.createFromAsset(assets,
            "font/inter_light.otf")
        findViewById<TextView>(R.id.roboto_text).typeface = Typeface.createFromAsset(assets,
            "font/roboto_flex_regular.ttf")
        findViewById<TextView>(R.id.sf_ui_display_text).typeface = Typeface.createFromAsset(assets,
            "font/sf_ui_display_light.otf")
    }
}