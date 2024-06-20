package com.example.diabetter.view.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diabetter.data.api.retrofit.RetrofitApi
import com.example.diabetter.data.api.user.UserApi
import com.example.diabetter.data.api.user.register.RegisterRequest
import com.example.diabetter.data.api.user.register.RegisterResponse
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.databinding.ActivitySignUpBinding
import com.example.diabetter.view.personalization.PersonalizationActivity
import com.example.diabetter.view.signin.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var loginPreferences: LoginPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginPreferences = LoginPreferences(this)
        initAction()
    }

    private fun initAction() {
        binding.btnSignup.setOnClickListener {
            register()
        }

        binding.tvSignin.setOnClickListener{
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun register() {
        val request = RegisterRequest(binding.etEmail.text.toString(), binding.etPassword.text.toString(), binding.etConfirm.text.toString())

        val retrofit = RetrofitApi().getRetrofitClientInstance().create(UserApi::class.java)
        retrofit.register(request).enqueue(object: Callback<RegisterResponse> {
            override fun onResponse(p0: Call<RegisterResponse>, p1: Response<RegisterResponse>) {
                if (p1.isSuccessful) {
                    val registerResponse = p1.body()!!
                    val message = registerResponse.message
                    val uid = registerResponse.uid
                    loginPreferences.storeUid(uid)
                    loginPreferences.storeEmail(binding.etEmail.text.toString())
                    showToast(message)

                    startActivity(Intent(this@SignUpActivity, PersonalizationActivity::class.java))
                } else {
                    val errorMsg = p1.code().toString()
                    showToast(errorMsg)
                }
            }

            override fun onFailure(p0: Call<RegisterResponse>, p1: Throwable) {
                Log.e("Error", p1.message.toString())
            }

        })
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}