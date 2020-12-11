package com.example.finalstudenthandbook

import android.content.Context
import android.content.SharedPreferences

class LoggingIn(context: Context) {
    private val PREFS_NAME = "sscthandbook"
    val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUsername(KEY_NAME: String, text: String) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, text)

        editor!!.commit()
    }

    fun saveLogin(KEY_NAME: String, status: Boolean) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putBoolean(KEY_NAME, status!!)

        editor.commit()
    }

    fun saveUserType(KEY_NAME: String, text: String) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, text)

        editor!!.commit()
    }

    fun getUserType(KEY_NAME: String): String? {

        return sharedPref.getString(KEY_NAME, null)
    }

    fun getUsername(KEY_NAME: String): String? {

        return sharedPref.getString(KEY_NAME, null)


    }

    fun getIsLogin(KEY_NAME: String, defaultValue: Boolean): Boolean {

        return sharedPref.getBoolean(KEY_NAME, defaultValue)

    }

    fun clearSaveCredentials() {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        editor.clear()
        editor.commit()
    }

    fun removeValue(KEY_NAME: String) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.remove(KEY_NAME)
        editor.commit()
    }
}