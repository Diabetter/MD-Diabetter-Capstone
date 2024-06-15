package com.example.diabetter.view.personalization.ui.confirm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentActivityPersonalizationBinding
import com.example.diabetter.databinding.FragmentBodyPersonalizationBinding
import com.example.diabetter.databinding.FragmentConfirmPersonalizationBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.utils.ObtainViewModelFactory
import com.example.diabetter.view.personalization.PersonalizationActivity
import com.example.diabetter.view.personalization.ui.gender.GenderPersonalizationViewModel
import kotlinx.coroutines.launch

class ConfirmPersonalizationFragment : Fragment() {
    private var _binding: FragmentConfirmPersonalizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbarBinding : ToolbarPersonalizationBinding
    private lateinit var viewModel: ConfirmPersonalizationViewModel

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
}