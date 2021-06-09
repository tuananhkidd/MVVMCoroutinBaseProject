package com.group.base.di

import android.content.Context
import android.content.SharedPreferences
import com.group.core.define.Constants

class SharedPreference constructor(context: Context) {
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var mPrefs: SharedPreference? = null
        fun getInstance(context: Context) : SharedPreference {
            if(mPrefs == null){
                mPrefs = SharedPreference(context)
            }
            return mPrefs!!
        }

    }

    fun saveString(key: String, value: String){
        sharedPreferences.edit()?.putString(key, value)?.apply()
    }

    fun getString(key: String) = sharedPreferences.getString(key, "")

    fun saveBoolean(key: String, value: Boolean){
        sharedPreferences.edit()?.putBoolean(key, value)?.apply()
    }

    fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

    fun clearBoolean(key: String) = sharedPreferences.edit()?.remove(key)?.commit()

    fun clearAllData() = sharedPreferences.edit()?.clear()?.commit()
}