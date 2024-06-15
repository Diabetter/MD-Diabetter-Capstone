package com.example.diabetter.data.repository

import com.example.diabetter.data.preference.PersonalizationPreference

class PersonalizationRepository (private val personalizationPreference: PersonalizationPreference) {
    suspend fun saveGender(gender : String) = personalizationPreference.saveGender(gender)
    fun getGender() = personalizationPreference.getGender()

    suspend fun saveBodyCondition(age : Int, height : Int, weight : Int) = personalizationPreference.saveBodyCondition(age, height, weight)
    fun getBodyCondition() = personalizationPreference.getBodyCondition()

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