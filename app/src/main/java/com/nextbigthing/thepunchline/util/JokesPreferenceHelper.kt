package com.nextbigthing.thepunchline.util

import android.content.Context
import android.content.SharedPreferences

class JokesPreferenceHelper(context: Context) {
    private val preferenceName = "JokesPreference"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}