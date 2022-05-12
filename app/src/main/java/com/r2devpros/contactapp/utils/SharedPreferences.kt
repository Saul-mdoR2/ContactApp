package com.r2devpros.contactapp.utils

import android.content.Context
import android.util.Log

fun saveColor(context: Context, colorInt: Int) {
    Log.d("SharedPreferencesUtils_TAG", "saveColor: color int $colorInt ")
    val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_TAG, Context.MODE_PRIVATE)
    sharedPreferences.edit().apply {
        putInt(COLOR_INT_TAG, colorInt)
        apply()
    }
}

fun getSavedColor(context: Context): Int {
    Log.d("SharedPreferencesUtils_TAG", "getColor: ")
    val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_TAG, Context.MODE_PRIVATE)
    return sharedPreferences.getInt(COLOR_INT_TAG, 0)
}

fun removeColor(context: Context) {
    val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_TAG, Context.MODE_PRIVATE)
    sharedPreferences.edit().apply {
        remove(COLOR_INT_TAG)
        apply()
    }
}