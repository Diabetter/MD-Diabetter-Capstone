package com.example.diabetter.view.main.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.databinding.FragmentProfileBinding
import com.example.diabetter.view.edit_profile.EditProfileActivity
import com.example.diabetter.view.signin.SignInActivity


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private var loginPreferences: LoginPreferences? = null

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

        binding.btnEdit.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }


    private fun logout() {
        loginPreferences?.deleteUid()
        startActivity(Intent(requireContext(), SignInActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}