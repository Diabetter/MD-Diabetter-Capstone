package com.example.diabetter.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.diabetter.R
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.view.launcher.LauncherActivity
import com.example.diabetter.view.main.MainActivity
import com.example.diabetter.view.welcome.WelcomeActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.navigationBarColor = ContextCompat.getColor(this, R.color.primary_500)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val splashIntent = Intent(this@SplashActivity, LauncherActivity::class.java)
            startActivity(splashIntent)
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)}, SPLASH_DURATION)
    }

    companion object{
        const val SPLASH_DURATION : Long = 2000
    }
}