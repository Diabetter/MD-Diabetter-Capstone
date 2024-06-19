package com.example.diabetter.view.detail_food

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diabetter.R
import com.example.diabetter.data.MenuData
import com.example.diabetter.databinding.ActivityDetailFoodBinding
import com.example.diabetter.databinding.OtherFoodBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding

class DetailFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFoodBinding
    private lateinit var otherFoodBinding : OtherFoodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        otherFoodBinding = binding.otherFood

        binding.backBtn.setOnClickListener { onBackPressed() }

        val menuDataList = listOf(
            MenuData(10001.0),
            MenuData(10002.0),
            MenuData(10003.0),
            MenuData(10004.0),
        )

        val menuViews = listOf(
            otherFoodBinding.menu1,
            otherFoodBinding.menu2,
            otherFoodBinding.menu3,
            otherFoodBinding.menu4,
        )

        menuViews.forEachIndexed { index, menuView ->
            menuView.setOnClickListener {
                val intent = Intent(this, DetailFoodActivity::class.java).apply {
                    putExtra("menu_data", menuDataList[index])
                }
                startActivity(intent)
            }
        }
    }
}