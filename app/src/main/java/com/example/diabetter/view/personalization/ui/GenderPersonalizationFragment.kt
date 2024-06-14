package com.example.diabetter.view.personalization.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentGenderPersonalizationBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.view.personalization.PersonalizationActivity
import com.example.diabetter.view.personalization.interfaces.GenderChangeListener

class GenderPersonalizationFragment : Fragment() {

    private var _binding: FragmentGenderPersonalizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbarBinding : ToolbarPersonalizationBinding

    private lateinit var radioButtonMan: RadioButton
    private lateinit var radioButtonWoman: RadioButton

    private lateinit var genderChangeListener: GenderChangeListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = _binding ?: FragmentGenderPersonalizationBinding.inflate(inflater, container, false)
        val view = binding.root

        radioButtonMan = binding.ivMan
        radioButtonWoman = binding.ivWoman

        radioButtonMan.setOnClickListener {
            radioButtonMan.isChecked = true
            radioButtonWoman.isChecked = false
            radioButtonMan.alpha = 1f
            radioButtonWoman.alpha = 0.5f
            genderChangeListener.onGenderChanged("Pria")
        }

        radioButtonWoman.setOnClickListener {
            radioButtonWoman.isChecked = true
            radioButtonMan.isChecked = false
            radioButtonWoman.alpha = 1f
            radioButtonMan.alpha = 0.5f
            genderChangeListener.onGenderChanged("Wanita")
        }

        return view
    }
    override fun onViewCreated( view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = (activity as PersonalizationActivity).retrieveBinding()
        binding.btnNext.setOnClickListener {
            (activity as? PersonalizationActivity)?.nextStep()
        }
        toolbarBinding = ToolbarPersonalizationBinding.bind(binding.toolbar)
        toolbarBinding.tvTitle.text = getString(R.string.gender_title)

        updateRadioButtonAlpha()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            genderChangeListener = context as GenderChangeListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement GenderChangeListener")
        }
    }

    private fun updateRadioButtonAlpha() {
        if (radioButtonMan.isChecked) {
            radioButtonMan.alpha = 1f
            radioButtonWoman.alpha = 0.5f
        } else if (radioButtonWoman.isChecked) {
            radioButtonWoman.alpha = 1f
            radioButtonMan.alpha = 0.5f
        } else {
            radioButtonMan.alpha = 0.5f
            radioButtonWoman.alpha = 0.5f
        }
    }
}