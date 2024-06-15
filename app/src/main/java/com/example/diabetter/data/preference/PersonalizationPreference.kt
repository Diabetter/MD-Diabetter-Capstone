package com.example.diabetter.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "personalization_preference")
class PersonalizationPreference private constructor(private val dataStore: DataStore<Preferences>){
    private val gender = stringPreferencesKey("genderPersonalization")

    private val age = intPreferencesKey("agePersonalization")
    private val weight = intPreferencesKey("weightPersonalization")
    private val height = intPreferencesKey("heightPersonalization")

    private val activity = stringPreferencesKey("activityPersonalization")

    suspend fun saveGender(genderValue : String){
        dataStore.edit { preferences ->
            preferences[gender] = genderValue
        }
    }

    fun getGender() : Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[gender] ?: ""
        }
    }

    suspend fun saveBodyCondition(ageValue : Int, weightValue : Int, heightValue : Int){
        dataStore.edit { preferences ->
            preferences[age] = ageValue
            preferences[weight] = weightValue
            preferences[height] = heightValue
        }
    }

    fun getBodyCondition() : Flow<Triple<Int, Int, Int>> {
        return dataStore.data.map { preferences ->
            Triple(
                preferences[age] ?: 0,
                preferences[weight] ?: 0,
                preferences[height] ?: 0
            )
        }
    }

    suspend fun saveActivity(activityValue : String){
        dataStore.edit { preferences ->
            preferences[activity] = activityValue
        }
    }

    fun getActivity() : Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[activity] ?: ""
        }
    }

    suspend fun deleteAllPreferences() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PersonalizationPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): PersonalizationPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = PersonalizationPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}