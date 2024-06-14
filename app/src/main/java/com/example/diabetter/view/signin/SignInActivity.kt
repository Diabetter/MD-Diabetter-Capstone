package com.example.diabetter.view.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diabetter.databinding.ActivitySignInBinding
import com.example.diabetter.view.main.MainActivity
import com.example.diabetter.view.personalization.PersonalizationActivity
import com.example.diabetter.view.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var  binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignup.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnSignIn.setOnClickListener{
            startActivity(Intent(this, PersonalizationActivity::class.java))
        }

    }
}