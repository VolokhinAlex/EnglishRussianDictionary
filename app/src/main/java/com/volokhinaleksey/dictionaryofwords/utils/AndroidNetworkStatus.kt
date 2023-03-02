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

/**
 * Implementation of the interface for receiving data from the network on smartphones with Android OS
 */


class AndroidNetworkStatus(context: Context) : NetworkStatus {

    private val connectivityManager = context.getSystemService<ConnectivityManager>()

    override fun observe(): Flow<NetworkStatus.Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(NetworkStatus.Status.Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(NetworkStatus.Status.Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(NetworkStatus.Status.Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(NetworkStatus.Status.Unavailable) }
                }
            }

            connectivityManager?.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager?.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }

}