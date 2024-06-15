package com.example.diabetter.di

import android.content.Context
import com.example.diabetter.data.preference.PersonalizationPreference
import com.example.diabetter.data.preference.dataStore
import com.example.diabetter.data.repository.PersonalizationRepository

object InjectPersonalizationPreference {
    fun provideRepository(context : Context) : PersonalizationRepository{
        val pref = PersonalizationPreference.getInstance(context.dataStore)
        return PersonalizationRepository(pref)
    }
}