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

    fun isUserLoggedIn(): Boolean {
        val uid = retrieveUid()
        return uid != null
    }

    fun deleteUid() {
        sharedPreferences.edit().remove("uid").commit()
    }
}