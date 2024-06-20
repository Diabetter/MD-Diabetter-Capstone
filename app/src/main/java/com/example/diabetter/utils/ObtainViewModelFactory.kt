package com.example.diabetter.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.diabetter.view.viewmodelfactory.PersonalizationViewModelFactory
import com.example.diabetter.view.viewmodelfactory.ViewModelFactory

class ObtainViewModelFactory {
    companion object {
        inline fun <reified T : ViewModel> obtainPersonalizationFactory(owner: Context): T {
            val factory = PersonalizationViewModelFactory.getInstance(owner)
            return ViewModelProvider(owner as ViewModelStoreOwner, factory)[T::class.java]
        }

        inline fun <reified T : ViewModel> obtainViewModelFactory(owner: Context): T {
            val factory = ViewModelFactory.getInstance(owner)
            return ViewModelProvider(owner as ViewModelStoreOwner, factory)[T::class.java]
        }
    }
}
