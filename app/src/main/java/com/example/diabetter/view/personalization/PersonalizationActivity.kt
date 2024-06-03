package com.example.diabetter.view.personalization

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.diabetter.R
import com.example.diabetter.databinding.ActivityPersonalizationBinding
import com.example.diabetter.view.welcome.WelcomeActivity

class PersonalizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalizationBinding
    fun retrieveBinding(): ActivityPersonalizationBinding {
        return binding
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val initialFragment = GenderPersonalizationFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_container, initialFragment)
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)

        if (currentFragment is BodyPersonalizationFragment) {
            binding.lineStep12.setImageResource(R.drawable.line_step_inactive)
            binding.stepIndicatorCircle1.setImageResource(R.drawable.step_indicator_circle_active)
            binding.stepIndicatorCircle2.setImageResource(R.drawable.step_indicator_circle_inactive)
        } else if (currentFragment is GenderPersonalizationFragment) {
            binding.lineStep12.setImageResource(R.drawable.line_step_inactive)
            binding.stepIndicatorCircle1.setImageResource(R.drawable.step_indicator_circle_inactive)
            binding.stepIndicatorCircle2.setImageResource(R.drawable.step_indicator_circle_inactive)
        }

        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
//            finish()
            startActivity(Intent(this, WelcomeActivity::class.java))
        }

    }
    fun navigateToNextFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)

        if (currentFragment is GenderPersonalizationFragment) {
            val bodyFragment = BodyPersonalizationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, bodyFragment)
                .addToBackStack(null)
                .commit()
            binding.lineStep12.setImageResource(R.drawable.line_step_active)
            binding.stepIndicatorCircle2.setImageResource(R.drawable.step_indicator_circle_active)
        } else if (currentFragment is BodyPersonalizationFragment) {


        }
    }
}