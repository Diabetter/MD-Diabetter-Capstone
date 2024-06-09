package com.example.diabetter.view.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diabetter.databinding.ActivitySignUpBinding
import com.example.diabetter.view.personalization.PersonalizationActivity
import com.example.diabetter.view.signin.SignInActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, PersonalizationActivity::class.java))
        }

        binding.tvSignin.setOnClickListener{
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}