package com.example.diabetter.view.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diabetter.data.repository.MenuRepository
import com.example.diabetter.di.Injection

class ViewModelFactory private constructor(private val menuRepository: MenuRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val constructor = modelClass.getConstructor(menuRepository::class.java)
        return constructor.newInstance(menuRepository)
    }
}