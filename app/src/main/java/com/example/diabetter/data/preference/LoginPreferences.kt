package com.example.diabetter.data.preference

import android.content.Context

class LoginPreferences(context: Context) {
    private val sharedPreferences by lazy {
        val prefName = "login_prefs"
        val mode = Context.MODE_PRIVATE
        context.getSharedPreferences(prefName, mode)
    }

    fun storeUid(uid: String) {
        val editor = sharedPreferences.edit()
        editor.putString("uid", uid)
        editor.apply()
    }

    fun retrieveUid(): String? {
        return sharedPreferences.getString("uid", null)
    }

    fun deleteUid() {
        sharedPreferences.edit().remove("uid").apply()
    }

    fun storeEmail(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.apply()
    }

    fun retrieveEmail(): String? {
        return sharedPreferences.getString("email", null)
    }

    fun deleteEmail() {
        val editor = sharedPreferences.edit()
        editor.remove("email")
        editor.apply()
    }

    fun isUserLoggedIn(): Boolean {
        val uid = retrieveUid()
        return uid != null
    }
}