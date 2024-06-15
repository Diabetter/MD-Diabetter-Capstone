package com.example.diabetter.view.personalization.ui.body

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetter.data.repository.PersonalizationRepository
import kotlinx.coroutines.launch

class BodyPersonalizationViewModel(
    private val personalizationRepository: PersonalizationRepository
) : ViewModel() {
    init {
        getBodyCondition()
    }
    private var age: Int = 0
    private var weight: Int = 0
    private var height: Int = 0
    fun saveBodyPersonalization(age : Int, height : Int, weight : Int) {
        viewModelScope.launch {
            personalizationRepository.saveBodyCondition(age, height, weight)
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
    fun getAge(): Int = age
    fun getWeight(): Int = weight
    fun getHeight(): Int = height
}