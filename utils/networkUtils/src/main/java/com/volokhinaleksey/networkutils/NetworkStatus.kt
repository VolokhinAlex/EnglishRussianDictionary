package com.volokhinaleksey.networkutils

import kotlinx.coroutines.flow.Flow


/**
 * Interface for getting information about the network
 * The interface returns an object that can be monitored
 * when subscribing to network availability.
 */

interface NetworkStatus {

    fun networkObserve(): Flow<Boolean>

}