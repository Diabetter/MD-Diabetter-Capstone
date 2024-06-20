package com.example.diabetter.view.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diabetter.data.api.retrofit.RetrofitApi
import com.example.diabetter.data.api.user.UserApi
import com.example.diabetter.data.api.user.login.LoginRequest
import com.example.diabetter.data.api.user.login.LoginResponse
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.databinding.ActivitySignInBinding
import com.example.diabetter.view.main.MainActivity
import com.example.diabetter.view.signup.SignUpActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInActivity : AppCompatActivity() {
    private lateinit var  binding: ActivitySignInBinding
    private lateinit var loginPreferences: LoginPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginPreferences = LoginPreferences(this)
        initAction()
    }

    private fun initAction() {
        binding.tvSignup.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnSignIn.setOnClickListener{
            login()
        }
    }

    private fun login() {
        val request = LoginRequest(binding.etEmail.text.toString(), binding.etPassword.text.toString())

        val retrofit = RetrofitApi().getRetrofitClientInstance().create(UserApi::class.java)
        retrofit.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(p0: Call<LoginResponse>, p1: Response<LoginResponse>) {
                if (p1.isSuccessful) {
                    val loginResponse = p1.body()!!
                    val message = loginResponse.message
                    val uid = loginResponse.uid
                    loginPreferences.storeUid(uid)
                    showToast(message)

                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                } else {
                    val errorMsg = "Login Failed: " + p1.code().toString()
                    showToast(errorMsg)
                }

            }

            override fun onFailure(p0: Call<LoginResponse>, p1: Throwable) {
                Log.e("Error", p1.message.toString())
            }

        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}