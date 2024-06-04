package com.example.diabetter.view.personalization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diabetter.R
import com.example.diabetter.databinding.ToolbarPersonalizationBinding

class ActivityPersonalizationFragment : Fragment() {
    private lateinit var toolbarBinding: ToolbarPersonalizationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity_personalization, container, false)
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
}