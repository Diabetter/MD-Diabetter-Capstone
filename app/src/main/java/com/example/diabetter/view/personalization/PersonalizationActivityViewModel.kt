package com.example.diabetter.view.personalization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diabetter.data.repository.PersonalizationRepository
import kotlinx.coroutines.launch

class PersonalizationActivityViewModel(
    private val personalizationRepository: PersonalizationRepository
) : ViewModel() {
    fun deleteAllPreferences() {
        viewModelScope.launch {
            personalizationRepository.deleteAllPreferences()
        }
    }
}