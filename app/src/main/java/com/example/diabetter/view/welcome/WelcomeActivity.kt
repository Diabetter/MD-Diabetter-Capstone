package com.example.diabetter.view.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diabetter.R
import com.example.diabetter.databinding.ActivityWelcomeBinding
import com.example.diabetter.view.personalization.PersonalizationActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            startActivity(Intent(this, PersonalizationActivity::class.java))
        }
        binding.haveAccount.setOnClickListener {
            startActivity(Intent(this, PersonalizationActivity::class.java))
        }
    }
}