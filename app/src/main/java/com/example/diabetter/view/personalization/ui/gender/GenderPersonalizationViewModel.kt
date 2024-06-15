package com.example.diabetter.view.personalization.ui.gender

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetter.data.repository.PersonalizationRepository
import kotlinx.coroutines.launch

class GenderPersonalizationViewModel(
    private val personalizationRepository: PersonalizationRepository
) : ViewModel() {
    init {
        getGender()
    }
    private var gender : String = ""

    fun saveGender(gender: String) {
        viewModelScope.launch {
            personalizationRepository.saveGender(gender)
        }
    }

    private fun getGender(){
        viewModelScope.launch {
            personalizationRepository.getGender().collect { genderValue ->
                gender = genderValue
            }
        }
    }
    fun getGenderValue(): String = gender

}