package com.example.diabetter.view.detail_menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diabetter.R
import com.example.diabetter.databinding.ActivityDetailFoodBinding
import com.example.diabetter.databinding.ActivityDetailMenuBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding

class DetailMenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailMenuBinding
    private lateinit var toolbarBinding : ToolbarPersonalizationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarBinding = ToolbarPersonalizationBinding.bind(binding.toolbar)

        toolbarBinding.backBtn.setOnClickListener { onBackPressed() }
        toolbarBinding.tvTitle.text = "Menu"
    }
}