package com.delta22.rentateamtest.data

import android.content.SharedPreferences

object SharedPrefHelper {
    private lateinit var pref: SharedPreferences

    private const val IS_DATA_SAVED = "IS_DATA_SAVED"

    fun init(sharedPreferences: SharedPreferences) {
        pref = sharedPreferences
    }

    fun saveData() {
        val ed = pref.edit()
        ed.putBoolean(IS_DATA_SAVED, Repository.isDataSaved)
        ed.apply()
    }

    fun loadData() {
        Repository.isDataSaved = pref.getBoolean(IS_DATA_SAVED, false)
    }
}
