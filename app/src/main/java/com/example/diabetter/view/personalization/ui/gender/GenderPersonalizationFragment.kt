package com.example.diabetter.view.personalization.ui.gender

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentGenderPersonalizationBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.utils.ObtainViewModelFactory
import com.example.diabetter.view.personalization.PersonalizationActivity
import com.example.diabetter.view.personalization.PersonalizationActivityViewModel
import com.example.diabetter.view.personalization.ui.body.BodyPersonalizationViewModel

class GenderPersonalizationFragment : Fragment() {

    private var _binding: FragmentGenderPersonalizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbarBinding : ToolbarPersonalizationBinding
    private lateinit var viewModel: GenderPersonalizationViewModel
    private lateinit var viewModelActivity: PersonalizationActivityViewModel

    private lateinit var radioButtonMan: RadioButton
    private lateinit var radioButtonWoman: RadioButton
    private var gender : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelActivity = ObtainViewModelFactory.obtainPersonalizationFactory(requireContext())
        viewModelActivity.deleteAllPreferences()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenderPersonalizationBinding.inflate(inflater, container, false)
        val view = binding.root

        radioButtonMan = binding.ivMan
        radioButtonWoman = binding.ivWoman

        radioButtonMan.setOnClickListener {
            radioButtonMan.isChecked = true
            radioButtonWoman.isChecked = false
            radioButtonMan.alpha = 1f
            radioButtonWoman.alpha = 0.5f
            gender = "Male"
            viewModel.saveGender(gender)
        }

        radioButtonWoman.setOnClickListener {
            radioButtonWoman.isChecked = true
            radioButtonMan.isChecked = false
            radioButtonWoman.alpha = 1f
            radioButtonMan.alpha = 0.5f
            gender = "Female"
            viewModel.saveGender(gender)
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
                viewModel.saveGender(gender)
            }
            toolbarBinding = ToolbarPersonalizationBinding.bind(binding.toolbar)
            toolbarBinding.tvTitle.text = getString(R.string.gender_title)
        }
        updateRadioButtonAlpha()
    }

    private fun updateRadioButtonAlpha() {
        if (viewModel.getGenderValue() == "Male") {
            radioButtonMan.alpha = 1f
            radioButtonWoman.alpha = 0.5f
        } else if (viewModel.getGenderValue() == "Female") {
            radioButtonWoman.alpha = 1f
            radioButtonMan.alpha = 0.5f
        } else {
            radioButtonMan.alpha = 0.5f
            radioButtonWoman.alpha = 0.5f
        }
    }
}