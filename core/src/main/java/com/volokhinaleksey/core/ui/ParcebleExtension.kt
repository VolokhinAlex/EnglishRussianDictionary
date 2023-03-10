package com.volokhinaleksey.core.ui

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable

const val DATA_KEY = "data_key"

/**
 * Extension to fix the deprecated getParcelable method, which became deprecated with API 33
 *
 * If the API of the application is lower than 33, then the usual getParcelable with the key will be used.
 * If the API of the application is equal to or higher than 33,then a constructor with a key and a
 * java class (Generic <T>) for which data is obtained will be used
 */

inline fun <reified T : Parcelable> Bundle.parcelable(): T? = when {
    SDK_INT >= 33 -> getParcelable(DATA_KEY, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(DATA_KEY) as? T
}