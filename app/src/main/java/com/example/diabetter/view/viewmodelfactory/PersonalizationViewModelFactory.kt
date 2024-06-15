package com.example.diabetter.view.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diabetter.data.repository.PersonalizationRepository
import com.example.diabetter.di.InjectPersonalizationPreference

class PersonalizationViewModelFactory private constructor(private val personalizationRepository: PersonalizationRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: PersonalizationViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): PersonalizationViewModelFactory {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = PersonalizationViewModelFactory(InjectPersonalizationPreference.provideRepository(context))
                }
            }
            return INSTANCE as PersonalizationViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val constructor = modelClass.getConstructor(personalizationRepository::class.java)
        return constructor.newInstance(personalizationRepository)
    }
}