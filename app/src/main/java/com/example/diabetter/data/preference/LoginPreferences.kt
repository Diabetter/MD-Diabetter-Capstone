package com.example.diabetter.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class LoginPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        @Volatile
        private var INSTANCE: LoginPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): LoginPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = LoginPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}