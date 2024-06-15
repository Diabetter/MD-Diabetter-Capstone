package com.example.diabetter.data.repository

import com.example.diabetter.data.preference.PersonalizationPreference
import kotlinx.coroutines.flow.Flow

class PersonalizationRepository (private val personalizationPreference: PersonalizationPreference) {
    suspend fun saveGender(gender : String) = personalizationPreference.saveGender(gender)
    fun getGender() = personalizationPreference.getGender()

    suspend fun saveBodyCondition(age : Int, height : Int, weight : Int) = personalizationPreference.saveBodyCondition(age, height, weight)
    fun getBodyCondition() = personalizationPreference.getBodyCondition()

    suspend fun saveActivity(activity : String) = personalizationPreference.saveActivity(activity)
    fun getActivity() = personalizationPreference.getActivity()

    suspend fun deleteAllPreferences() = personalizationPreference.deleteAllPreferences()

    companion object {
        @Volatile
        private var instance: PersonalizationRepository? = null

        fun getInstance(
            preferences: PersonalizationPreference,
        ): PersonalizationRepository =
            instance ?: synchronized(this) {
                instance ?: PersonalizationRepository(
                    preferences,
                ).also {
                    instance = it
                }
            }
    }
}