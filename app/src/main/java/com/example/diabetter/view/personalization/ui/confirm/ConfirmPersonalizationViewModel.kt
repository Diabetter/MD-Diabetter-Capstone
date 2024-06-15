package com.example.diabetter.view.personalization.ui.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetter.data.repository.PersonalizationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConfirmPersonalizationViewModel(
    private val personalizationRepository: PersonalizationRepository
) : ViewModel () {
    init{
        getGender()
        getBodyCondition()
        getActivity()
    }
    private var gender : String = ""
    private var age: Int = 0
    private var weight: Int = 0
    private var height: Int = 0
    private var activity : String = ""

    private fun getGender(){
        viewModelScope.launch {
            personalizationRepository.getGender().collect { genderValue ->
                gender = genderValue
            }
        }
    }

    private fun getBodyCondition(){
        viewModelScope.launch {
            personalizationRepository.getBodyCondition().collect { (ageValue, weightValue, heightValue) ->
                age = ageValue
                weight = weightValue
                height = heightValue
            }
        }
    }

    private fun getActivity(){
        viewModelScope.launch {
            personalizationRepository.getActivity().collect{activityValue ->
                activity = activityValue
            }
        }
    }

    fun getAge(): Int = age
    fun getWeight(): Int = weight
    fun getHeight(): Int = height
    fun getGenderValue(): String = gender
    fun getActivityValue(): String = activity
}