package com.example.diabetter.view.edit_profile

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diabetter.R
import com.example.diabetter.data.api.retrofit.RetrofitApi
import com.example.diabetter.data.api.user.UserApi
import com.example.diabetter.data.api.user.profile.edit.EditUserProfileRequest
import com.example.diabetter.data.api.user.profile.edit.EditUserProfileResponse
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.databinding.ActivityEditProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditProfileBinding
    private lateinit var loginPreferences: LoginPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginPreferences = LoginPreferences(this)

        initAction()
    }

    private fun initAction() {
        binding.btnSave.setOnClickListener {
            saveProfile()
        }

        binding.backBtn.setOnClickListener{
            onBackPressed()
        }
    }

    private fun saveProfile() {
        val username = binding.etUsername.text.toString()
        val age = Integer.parseInt(binding.etAge.text.toString())
        val gender = "Female" // Assuming gender selection (modify if needed)
        val height = Integer.parseInt(binding.etHeight.text.toString())
        val weight = Integer.parseInt(binding.etWeight.text.toString())
        val userId = loginPreferences.retrieveUid() // Get user ID

        var request: EditUserProfileRequest? = null

        when(binding.rgActivity.checkedRadioButtonId) {
            R.id.rb0 -> request = userId?.let {
                EditUserProfileRequest("ringan", age, gender, height,
                    it, username, weight)
            }

            R.id.rb1 -> request = userId?.let {
                EditUserProfileRequest("sedang", age, gender, height,
                    it, username, weight)
            }

            R.id.rb2 -> request = userId?.let {
                EditUserProfileRequest("berat", age, gender, height,
                    it, username, weight)
            }
        }

        val retrofit = RetrofitApi().getRetrofitClientInstance().create(UserApi::class.java)
        if (request != null) {
            retrofit.editProfile(request).enqueue(object : Callback<EditUserProfileResponse> {
                override fun onResponse(
                    p0: Call<EditUserProfileResponse>,
                    p1: Response<EditUserProfileResponse>
                ) {
                    if (p1.isSuccessful) {
                        val editUserProfileResponse = p1.body()!!
                        val message = editUserProfileResponse.message
                        binding.tvUsernameTitle.text = username
                        showToast(message)
                    } else {
                        val errorMsg = p1.code().toString()
                        showToast(errorMsg)
                    }
                }

                override fun onFailure(p0: Call<EditUserProfileResponse>, p1: Throwable) {
                    Log.e("Error", p1.message.toString())
                }

            })
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}