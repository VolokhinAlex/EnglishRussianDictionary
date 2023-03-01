package com.volokhinaleksey.dictionaryofwords.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<T : WordsState> : ViewModel() {

    protected val currentMutableData: MutableLiveData<T> = MutableLiveData()
    protected val compositeDisposable = CompositeDisposable()

    val currentData: LiveData<T> get() = currentMutableData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}