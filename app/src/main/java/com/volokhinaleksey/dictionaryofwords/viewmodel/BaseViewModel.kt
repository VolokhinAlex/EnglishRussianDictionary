package com.volokhinaleksey.dictionaryofwords.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * The base class for all ViewModels, which includes the initial configuration to work with different methods.
 */

abstract class BaseViewModel<T : Any> : ViewModel() {

    protected val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    protected val currentMutableData: MutableSharedFlow<T> = MutableSharedFlow()

    val currentData: LiveData<T> = currentMutableData.asLiveData()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}