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
        _binding = FragmentGenderPersonalizationBinding.inflate(inflater, container, false)
        val view = binding.root

        radioButtonMan = binding.ivMan
        radioButtonWoman = binding.ivWoman

        radioButtonMan.setOnClickListener {
            radioButtonWoman.isChecked = false
            genderChangeListener.onGenderChanged("Pria")
        }

        radioButtonWoman.setOnClickListener {
            radioButtonMan.isChecked = false
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
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            genderChangeListener = context as GenderChangeListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement GenderChangeListener")
        }
    }
}