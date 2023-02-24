package com.volokhinaleksey.dictionaryofwords.repository

/**
 * Interface for working with the remote API.
 */

interface ApiHolder {
    val apiService: ApiService
}

/**
 * Implementation of an interface for working with a remote API using Retrofit.
 */

class DictionaryApiHolder(override val apiService: ApiService) : ApiHolder