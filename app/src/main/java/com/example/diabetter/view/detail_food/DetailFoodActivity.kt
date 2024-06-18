package com.example.diabetter.view.detail_food

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diabetter.R
import com.example.diabetter.data.MenuData

class DetailFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_food)

        val menuData: MenuData? = intent.getParcelableExtra("menu_data")
        Log.d("Testt", menuData?.calories.toString())
    }
}