package com.example.diabetter.view.personalization

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.diabetter.R
import com.example.diabetter.databinding.ActivityPersonalizationBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.view.main.MainActivity
import com.example.diabetter.view.personalization.interfaces.ActivityChangeListener
import com.example.diabetter.view.personalization.interfaces.GenderChangeListener
import com.example.diabetter.view.signup.SignUpActivity
import com.example.diabetter.view.personalization.ui.ActivityPersonalizationFragment
import com.example.diabetter.view.personalization.ui.BodyPersonalizationFragment
import com.example.diabetter.view.personalization.ui.ConfirmPersonalizationFragment
import com.example.diabetter.view.personalization.ui.GenderPersonalizationFragment
import com.example.diabetter.view.welcome.WelcomeActivity

class PersonalizationActivity : AppCompatActivity(), GenderChangeListener, ActivityChangeListener {
    private lateinit var binding: ActivityPersonalizationBinding
    private lateinit var toolbarBinding : ToolbarPersonalizationBinding
    private var touchWithinBounds = true
    private var currentGender : String? = null
    private var currentActivity : String? = null
    fun retrieveBinding(): ActivityPersonalizationBinding {
        return binding
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_container, GenderPersonalizationFragment())
            .commit()
        toolbarBinding = ToolbarPersonalizationBinding.bind(binding.toolbar)
        toolbarBinding.backBtn.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.alpha = 0.5f
                    touchWithinBounds = true
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val rect = Rect()
                    v.getHitRect(rect)
                    touchWithinBounds = rect.contains(event.x.toInt(), event.y.toInt())
                    true
                }
                MotionEvent.ACTION_UP -> {
                    v.alpha = 1f
                    if (touchWithinBounds) {
                        v.performClick()
                    }
                    false
                }
                MotionEvent.ACTION_CANCEL -> {
                    v.alpha = 1f
                    touchWithinBounds = false
                    false
                }
                else -> false
            }
        }
        toolbarBinding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)

        if (currentFragment is BodyPersonalizationFragment) {
            setStepIndicatorState(binding.lineStep12, false, "line")
            setStepIndicatorState(binding.stepIndicatorCircle2, false, "circle")
        }else if (currentFragment is ActivityPersonalizationFragment){
            setStepIndicatorState(binding.lineStep23, false, "line")
            setStepIndicatorState(binding.stepIndicatorCircle3, false, "circle")
        }else if(currentFragment is ConfirmPersonalizationFragment){
            setStepIndicatorState(binding.lineStep34, false, "line")
            setStepIndicatorState(binding.stepIndicatorCircle4, false, "circle")
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    override fun onGenderChanged(gender: String) {
        currentGender = gender
        Log.d("Testt", "Gender: $currentGender")
    }

    override fun onChangeActivity(activity: String) {
        currentActivity = activity
        Log.d("Testt", "Activity :  $currentActivity" )
    }

    fun nextStep() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)

        if (currentFragment is GenderPersonalizationFragment) {
            val nextFragment = BodyPersonalizationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, nextFragment)
                .addToBackStack(null)
                .commit()
            setStepIndicatorState(binding.lineStep12, true, "line")
            setStepIndicatorState(binding.stepIndicatorCircle2, true, "circle")
//            Log.d("Testt", currentGender!!)
        } else if (currentFragment is BodyPersonalizationFragment) {
            val nextFragment = ActivityPersonalizationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, nextFragment)
                .addToBackStack(null)
                .commit()
            setStepIndicatorState(binding.lineStep23, true, "line")
            setStepIndicatorState(binding.stepIndicatorCircle3, true, "circle")
        } else if (currentFragment is ActivityPersonalizationFragment) {
            val nextFragment = ConfirmPersonalizationFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, nextFragment)
                .addToBackStack(null)
                .commit()
            setStepIndicatorState(binding.lineStep34, true, "line")
            setStepIndicatorState(binding.stepIndicatorCircle4, true, "circle")
        }
    }

    private fun setStepIndicatorState(imageView: ImageView, isActive: Boolean, type : String) {
        val activeCircle: Int = R.drawable.step_indicator_circle_active
        val inactiveCircle: Int = R.drawable.step_indicator_circle_inactive
        val activeStep : Int = R.drawable.line_step_active
        val inactiveStep : Int = R.drawable.line_step_inactive

        if (type == "circle") {
            if (isActive) {
                imageView.setImageResource(activeCircle)
            } else {
                imageView.setImageResource(inactiveCircle)
            }
        }else if (type == "line") {
            if (isActive) {
                imageView.setImageResource(activeStep)
            } else {
                imageView.setImageResource(inactiveStep)
            }
        }
    }


    fun moveToMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}