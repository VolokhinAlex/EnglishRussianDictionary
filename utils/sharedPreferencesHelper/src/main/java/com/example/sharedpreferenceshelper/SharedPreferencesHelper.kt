package com.example.sharedpreferenceshelper

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A delegate for convenient writing and reading from SharedPreferences.
 * The class accepts the following data types for writing and reading String, Int, Long, Float, Boolean,
 * otherwise it returns IllegalStateException with the error "Unknown data type"
 */

@Suppress("UNCHECKED_CAST")
class SharedPreferencesHelper<V>(
    private val sharedPreferences: SharedPreferences,
    private val keyName: String,
    private val defValue: V
) : ReadWriteProperty<Any?, V> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): V {
        return when (defValue) {
            is String -> sharedPreferences.getString(keyName, defValue) as? V ?: defValue
            is Int -> sharedPreferences.getInt(keyName, defValue) as? V ?: defValue
            is Long -> sharedPreferences.getLong(keyName, defValue) as? V ?: defValue
            is Float -> sharedPreferences.getFloat(keyName, defValue) as? V ?: defValue
            is Boolean -> sharedPreferences.getBoolean(keyName, defValue) as? V ?: defValue
            else -> error("Unknown data type")
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: V) {
        when (defValue) {
            is String -> sharedPreferences.edit().putString(keyName, value as? String).apply() as? V
                ?: defValue

            is Int -> sharedPreferences.edit().putInt(keyName, value as? Int ?: defValue)
                .apply() as? V ?: defValue

            is Long -> sharedPreferences.edit().putLong(keyName, value as? Long ?: defValue)
                .apply() as? V ?: defValue

            is Float -> sharedPreferences.edit().putFloat(keyName, value as? Float ?: defValue)
                .apply() as? V ?: defValue

            is Boolean -> sharedPreferences.edit()
                .putBoolean(keyName, value as? Boolean ?: defValue).apply() as? V ?: defValue

            else -> error("Unknown data type")
        }
    }

}