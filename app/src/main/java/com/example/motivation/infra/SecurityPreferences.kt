package com.example.motivation.infra

import android.content.Context
import android.content.SharedPreferences

const val USER_NAME = "USER_NAME"

class SecurityPreferences (context: Context) {

    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun store(key: String, name: String) {
        security.edit().putString(key, name).apply()
    }

    fun getKeyValue(key: String): String {
        return security.getString(key, "") ?: ""
    }

}