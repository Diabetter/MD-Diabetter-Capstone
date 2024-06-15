package com.example.diabetter.view.personalization.ui.body

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentBodyPersonalizationBinding
import com.example.diabetter.databinding.FragmentGenderPersonalizationBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.utils.ObtainViewModelFactory
import com.example.diabetter.view.personalization.PersonalizationActivity

class BodyPersonalizationFragment : Fragment() {
    private var _binding: FragmentBodyPersonalizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BodyPersonalizationViewModel
    private lateinit var toolbarBinding : ToolbarPersonalizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBodyPersonalizationBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ObtainViewModelFactory.obtainPersonalizationFactory(requireContext())

        val parentActivity = activity as? PersonalizationActivity
        parentActivity?.apply {
            val binding = retrieveBinding()
            binding.btnNext.setOnClickListener {
                nextStep()
                saveEditTextValues()
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
        if(binding.edAge.text.toString().isEmpty()){
            binding.edAge.setText("0")
        }
        if(binding.edWeight.text.toString().isEmpty()){
            binding.edWeight.setText("0")
        }
        if(binding.edHeight.text.toString().isEmpty()) {
            binding.edHeight.setText("0")
        }

        viewModel.saveBodyPersonalization(
            binding.edAge.text.toString().toInt(),
            binding.edWeight.text.toString().toInt(),
            binding.edHeight.text.toString().toInt()
        )
    }

    private fun restoreEditTextValues() {
        binding.edAge.setText(viewModel.getAge().toString())
        binding.edWeight.setText(viewModel.getWeight().toString())
        binding.edHeight.setText(viewModel.getHeight().toString())

        if(viewModel.getAge() == 0){
            binding.edAge.setText("")
        }
        if(viewModel.getWeight() == 0){
            binding.edWeight.setText("")
        }
        if (viewModel.getHeight() == 0) {
            binding.edHeight.setText("")
        }
    }
}

