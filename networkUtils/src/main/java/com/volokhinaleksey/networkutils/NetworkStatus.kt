package com.volokhinaleksey.networkutils

import io.reactivex.rxjava3.core.Observable


/**
 * Interface for getting information about the network
 * The interface returns an object that can be monitored
 * when subscribing to network availability.
 */

interface NetworkStatus {

    fun isNetworkAvailable() : Observable<Boolean>

}