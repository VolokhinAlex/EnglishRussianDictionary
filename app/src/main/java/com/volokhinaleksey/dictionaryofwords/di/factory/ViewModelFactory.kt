package com.volokhinaleksey.dictionaryofwords.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModels[modelClass] ?: viewModels.asIterable().firstOrNull() {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: error("Unknown ViewModel Class: $modelClass")

        return try {
            creator.get() as T
        } catch (e: java.lang.Exception) {
            throw java.lang.RuntimeException(e)
        }
    }

}