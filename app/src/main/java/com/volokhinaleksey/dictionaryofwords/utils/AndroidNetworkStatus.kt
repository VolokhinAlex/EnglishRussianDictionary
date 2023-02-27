package com.volokhinaleksey.dictionaryofwords.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class AndroidNetworkStatus(context: Context) : NetworkStatus {

    private val connectivityManager = context.getSystemService<ConnectivityManager>()

    override fun isNetworkAvailable(): Flow<Boolean> = callbackFlow {
        val request = NetworkRequest.Builder().build()
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                launch { send(true) }
            }

            override fun onUnavailable() {
                launch { send(false) }
            }

            override fun onLost(network: Network) {
                launch { send(false) }
            }
        }

        connectivityManager?.registerNetworkCallback(request, callback)
        awaitClose {
            connectivityManager?.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()

}