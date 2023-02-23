package com.volokhinaleksey.dictionaryofwords.repository

import javax.inject.Inject

/**
 * Interface for working with the remote API.
 */

interface ApiHolder {
    val apiService: ApiService
}

/**
 * Implementation of an interface for working with a remote API using Retrofit.
 */

class DictionaryApiHolder @Inject constructor(override val apiService: ApiService) : ApiHolder