package com.example.diabetter.view.personalization.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentActivityPersonalizationBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.utils.ObtainViewModelFactory
import com.example.diabetter.view.personalization.PersonalizationActivity

class ActivityPersonalizationFragment : Fragment() {
    private var _binding: FragmentActivityPersonalizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbarBinding: ToolbarPersonalizationBinding
    private lateinit var viewModel: ActivityPersonalizationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityPersonalizationBinding.inflate(inflater, container, false)
        val view = binding.root

        val radioGroup = binding.rgActivity
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_normal_activities -> viewModel.saveActivity("Normal")
                R.id.radio_mid_activity -> viewModel.saveActivity("Medium")
                R.id.radio_high_activity -> viewModel.saveActivity("High")
                else -> ""
            }
        }

        return view
    }
    override fun onViewCreated( view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ObtainViewModelFactory.obtainPersonalizationFactory(requireContext())

        val parentActivity = activity as? PersonalizationActivity
        parentActivity?.apply {
            val binding = retrieveBinding()
            binding.btnNext.setOnClickListener {
                nextStep()
            }
            toolbarBinding = ToolbarPersonalizationBinding.bind(binding.toolbar)
            toolbarBinding.tvTitle.text = getString(R.string.body_title)
        }
    }
}