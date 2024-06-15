package com.example.diabetter.view.personalization.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetter.data.repository.PersonalizationRepository
import kotlinx.coroutines.launch

class ActivityPersonalizationViewModel (
    private val personalizationRepository: PersonalizationRepository
) : ViewModel() {
    init {
        getActivity()
    }

    private var activity : String = ""

    fun saveActivity(activity: String){
        viewModelScope.launch {
            personalizationRepository.saveActivity(activity)
        }
    }

    private fun getActivity(){
        viewModelScope.launch {
            personalizationRepository.getActivity().collect{activityValue ->
                activity = activityValue
            }
        }
    }

    fun getActivityValue(): String{
        return activity
    }

}