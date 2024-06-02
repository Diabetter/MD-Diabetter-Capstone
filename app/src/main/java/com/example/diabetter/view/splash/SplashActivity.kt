package com.example.diabetter.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.diabetter.R
import com.example.diabetter.view.welcome.WelcomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val splashIntent = Intent(this@SplashActivity, WelcomeActivity::class.java)
            startActivity(splashIntent)
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)}, SPLASH_DURATION)
    }

    companion object{
        const val SPLASH_DURATION : Long = 2000
    }
}