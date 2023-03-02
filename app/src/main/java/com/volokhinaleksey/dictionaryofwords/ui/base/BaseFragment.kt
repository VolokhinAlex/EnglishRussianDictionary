package com.volokhinaleksey.dictionaryofwords.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.volokhinaleksey.dictionaryofwords.utils.AndroidNetworkStatus
import com.volokhinaleksey.dictionaryofwords.utils.NetworkStatus
import com.volokhinaleksey.dictionaryofwords.viewmodel.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Fragment base class for creating your fragments based on it
 */

abstract class BaseFragment<T : Any> : Fragment() {

    /**
     * Some kind of ViewModel that inherits from the base ViewModel
     */

    abstract val viewModel: BaseViewModel<T>

    /**
     * A method for processing data coming from outside
     */

    abstract fun renderData(state: T)

    protected var isNetworkAvailable: Boolean = false
    private var networkStatus: NetworkStatus? = null

    protected val compositeDisposable = CompositeDisposable()

    /**
     * Method for showing the loading status
     */

    abstract fun showViewOnLoading()

    /**
     * Method for showing the error status
     */

    abstract fun showViewOnError(error: String)

    /**
     * Method for showing the success status
     */

    abstract fun showViewOnSuccess()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkStatus = AndroidNetworkStatus(requireContext())
        networkStatus?.isNetworkAvailable()?.subscribe {
            isNetworkAvailable = it
        }?.let { compositeDisposable.add(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}