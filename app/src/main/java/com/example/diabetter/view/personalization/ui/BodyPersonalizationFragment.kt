package com.example.diabetter.view.personalization.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentBodyPersonalizationBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.view.personalization.PersonalizationActivity

class BodyPersonalizationFragment : Fragment() {
    private lateinit var binding: FragmentBodyPersonalizationBinding
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var toolbarBinding : ToolbarPersonalizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBodyPersonalizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentActivity = activity as? PersonalizationActivity
        parentActivity?.apply {
            val binding = retrieveBinding()
            binding.btnNext.setOnClickListener {
                nextStep()
            }
            toolbarBinding = ToolbarPersonalizationBinding.bind(binding.toolbar)
            toolbarBinding.tvTitle.text = getString(R.string.body_title)
        }

        restoreEditTextValues()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveEditTextValues()
    }

    private fun saveEditTextValues() {
        val context = requireContext()
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Age", binding.edAge.text.toString())
        editor.putString("Weight", binding.edWeight.text.toString())
        editor.putString("Height", binding.edHeight.text.toString())
        editor.apply()
    }

    private fun restoreEditTextValues() {
        val context = requireContext()
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        binding.edAge.setText(sharedPreferences.getString("Age", ""))
        binding.edWeight.setText(sharedPreferences.getString("Weight", ""))
        binding.edHeight.setText(sharedPreferences.getString("Height", ""))
    }
}
