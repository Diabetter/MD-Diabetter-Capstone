package com.example.diabetter.view.main.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.diabetter.data.api.retrofit.RetrofitApi
import com.example.diabetter.data.api.user.UserApi
import com.example.diabetter.data.api.user.profile.UserProfileRequest
import com.example.diabetter.data.api.user.profile.UserProfileResponse
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.databinding.FragmentProfileBinding
import com.example.diabetter.view.edit_profile.EditProfileActivity
import com.example.diabetter.view.history.HistoryActivity
import com.example.diabetter.view.signin.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val loginPreferences: LoginPreferences by lazy {
        LoginPreferences(requireContext())
    }

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding

        getProfiles()
        binding.btnEdit.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }

        binding.seeMore.setOnClickListener {
            startActivity(Intent(requireContext(), HistoryActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun getProfiles() {
        val request = loginPreferences.retrieveUid()?.let { UserProfileRequest(it) }

        val retrofit = RetrofitApi().getRetrofitClientInstance().create(UserApi::class.java)
        if (request != null) {
            retrofit.getProfile(request).enqueue(object : Callback<UserProfileResponse> {
                override fun onResponse(
                    p0: Call<UserProfileResponse>,
                    p1: Response<UserProfileResponse>
                ) {
                    if (p1.isSuccessful) {
                        val userProfileResponse = p1.body()!!
                        binding.tvUsername.text = userProfileResponse.username
                        binding.tvGender.text = userProfileResponse.gender
                        binding.tvAge.text = userProfileResponse.age.toString()
                        binding.tvAgeProfile.text = userProfileResponse.age.toString()
                        binding.tvWeight.text = userProfileResponse.weight.toString()
                        binding.tvHeight.text = userProfileResponse.height.toString()
                        binding.tvDaily.text = userProfileResponse.activities
                    } else {
                        val errorMsg = p1.code().toString()
                        showToast(errorMsg)
                    }
                }

                override fun onFailure(p0: Call<UserProfileResponse>, p1: Throwable) {
                    Log.e("Error: ", p1.message.toString())
                }

            })
        }
    }

    private fun logout() {
        loginPreferences.deleteUid()
        startActivity(Intent(requireContext(), SignInActivity::class.java))
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}