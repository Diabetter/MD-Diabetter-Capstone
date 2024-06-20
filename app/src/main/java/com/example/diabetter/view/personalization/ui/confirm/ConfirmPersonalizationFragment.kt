package com.example.diabetter.view.personalization.ui.confirm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.lifecycle.lifecycleScope
import com.example.diabetter.R
import com.example.diabetter.data.api.retrofit.RetrofitApi
import com.example.diabetter.data.api.user.UserApi
import com.example.diabetter.data.api.user.profile.create.CreateUserProfileRequest
import com.example.diabetter.data.api.user.profile.create.CreateUserProfileResponse
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.databinding.FragmentActivityPersonalizationBinding
import com.example.diabetter.databinding.FragmentBodyPersonalizationBinding
import com.example.diabetter.databinding.FragmentConfirmPersonalizationBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.utils.ObtainViewModelFactory
import com.example.diabetter.view.main.MainActivity
import com.example.diabetter.view.personalization.PersonalizationActivity
import com.example.diabetter.view.personalization.ui.gender.GenderPersonalizationViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfirmPersonalizationFragment : Fragment() {
    private var _binding: FragmentConfirmPersonalizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbarBinding : ToolbarPersonalizationBinding
    private lateinit var viewModel: ConfirmPersonalizationViewModel
    private val loginPreferences: LoginPreferences by lazy {
        LoginPreferences(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmPersonalizationBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }
    override fun onViewCreated( view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ObtainViewModelFactory.obtainPersonalizationFactory(requireContext())

        val parentActivity = activity as? PersonalizationActivity
        parentActivity?.apply {
            val binding = retrieveBinding()
            binding.btnNext.setOnClickListener {
                createProfile()
                moveToMainActivity()
            }
            toolbarBinding = ToolbarPersonalizationBinding.bind(binding.toolbar)
            toolbarBinding.tvTitle.text = getString(R.string.confirmation_title)
        }

        fetchData()
        bindValue()
    }
    private fun fetchData() {
        viewModel.getGender()
        viewModel.getBodyCondition()
        viewModel.getActivity()
    }

    private fun bindValue() {
        binding.tvGender.text = viewModel.getGenderValue()
        binding.tvAge.text = viewModel.getAge().toString()
        binding.tvHeight.text = viewModel.getHeight().toString()
        binding.tvWeight.text = viewModel.getWeight().toString()
        binding.tvDaily.text = viewModel.getActivityValue()
    }

    fun createProfile() {
        val uid = loginPreferences.retrieveUid()
        val request = uid?.let {
            loginPreferences.retrieveEmail()?.let { it1 ->
                CreateUserProfileRequest(binding.tvDaily.text.toString(), Integer.parseInt(binding.tvAge.text.toString()), binding.tvGender.text.toString(), Integer.parseInt(binding.tvHeight.text.toString()),
                    it,
                    it1, Integer.parseInt(binding.tvWeight.text.toString()))
            }
        }

        val retrofit = RetrofitApi().getRetrofitClientInstance().create(UserApi::class.java)
        if (request != null) {
            retrofit.createProfile(request).enqueue(object : Callback<CreateUserProfileResponse> {
                override fun onResponse(
                    p0: Call<CreateUserProfileResponse>,
                    p1: Response<CreateUserProfileResponse>
                ) {
                    if (p1.isSuccessful) {
                        val message = p1.body()!!.message
                        showToast(message)

                        loginPreferences.deleteEmail()
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                    } else {
                        val errorMsg = p1.code().toString()
                        showToast(errorMsg)
                    }
                }

                override fun onFailure(p0: Call<CreateUserProfileResponse>, p1: Throwable) {
                    Log.e("ERROR", p1.message.toString())
                }

            })
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}