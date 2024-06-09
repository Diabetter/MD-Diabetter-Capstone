package com.example.diabetter.view.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diabetter.databinding.ActivityWelcomeBinding
import com.example.diabetter.view.signin.SignInActivity
import com.example.diabetter.view.signup.SignUpActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.haveAccount.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}