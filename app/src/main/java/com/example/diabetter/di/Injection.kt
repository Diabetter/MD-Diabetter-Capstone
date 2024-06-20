package com.example.diabetter.di

import android.content.Context
import com.example.diabetter.data.preference.LoginPreferences
import com.example.diabetter.data.preference.dataStore
import com.example.diabetter.data.remote.retrofit.ApiConfig
import com.example.diabetter.data.repository.MenuRepository
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): MenuRepository {
        val apiService = ApiConfig.getApiSevice()
        return MenuRepository.getInstance(apiService)
    }
}