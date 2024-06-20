package com.example.diabetter.view.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.view.main.MainActivity
import com.example.diabetter.view.signin.SignInActivity

class LauncherActivity : AppCompatActivity() {
    private lateinit var loginPreferences: LoginPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPreferences = LoginPreferences(this)

        if (loginPreferences.isUserLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}