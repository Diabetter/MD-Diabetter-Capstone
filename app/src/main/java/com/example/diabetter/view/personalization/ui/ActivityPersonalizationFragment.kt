package com.example.diabetter.view.personalization.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.example.diabetter.R
import com.example.diabetter.databinding.FragmentActivityPersonalizationBinding
import com.example.diabetter.databinding.ToolbarPersonalizationBinding
import com.example.diabetter.view.personalization.PersonalizationActivity
import com.example.diabetter.view.personalization.interfaces.ActivityChangeListener

class ActivityPersonalizationFragment : Fragment() {
    private var _binding: FragmentActivityPersonalizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbarBinding: ToolbarPersonalizationBinding

    private lateinit var activityChangeListener: ActivityChangeListener

    private lateinit var radioGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityPersonalizationBinding.inflate(inflater, container, false)
        val view = binding.root

        val radioGroup = binding.rgActivity
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val activity = when (checkedId) {
                R.id.radio_normal_activities -> "normal"
                R.id.radio_mid_activity -> "medium"
                R.id.radio_high_activity -> "high"
                else -> ""
            }
            activityChangeListener.onChangeActivity(activity)
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
        toolbarBinding.tvTitle.text = getString(R.string.activity_title)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityChangeListener = context as ActivityChangeListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement GenderChangeListener")
        }
    }
}