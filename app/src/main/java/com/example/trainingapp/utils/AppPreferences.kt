package com.example.trainingapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class AppPreferences(context: Context) {
    private val arrayAuth =
        arrayOf("cif", "tokenLogin", "nama", "customerType", "tkdn", "apiKey", "version")
    private val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    var cif: String?
        get() = sharedPreferences.getString("cif", null)
        set(value) = sharedPreferences.edit { putString("cif", value) }

    var tokenlogin: String?
        get() = sharedPreferences.getString("tokenLogin", null)
        set(value) = sharedPreferences.edit { putString("tokenLogin", value) }
    var nama: String?
        get() = sharedPreferences.getString("nama", null)
        set(value) = sharedPreferences.edit { putString("nama", value) }
    var customerType: String?
        get() = sharedPreferences.getString("customerType", null)
        set(value) = sharedPreferences.edit { putString("customerType", value) }
    var tkdn: String?
        get() = sharedPreferences.getString("tkdn", null)
        set(value) = sharedPreferences.edit { putString("tkdn", value) }
    var apiKey: String?
        get() = sharedPreferences.getString("apiKey", null)
        set(value) = sharedPreferences.edit { putString("apiKey", value) }
    var versionStartRes: String?
        get() = sharedPreferences.getString("version", null)
        set(value) = sharedPreferences.edit { putString("version", value) }
    var flagIntro: Boolean
        get() = sharedPreferences.getBoolean("flagIntro", false)
        set(value) = sharedPreferences.edit { putBoolean("flagIntro", value) }
    var firebaseToken: String?
        get() = sharedPreferences.getString("firebaseToken", null)
        set(value) = sharedPreferences.edit { putString("firebaseToken", value) }

    fun isExist(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun clear() = sharedPreferences.edit().clear().apply()

    fun logOut() {
        with(sharedPreferences.edit()) {
            for (key in arrayAuth) {
                remove(key)
            }
            apply()
        }
    }

}