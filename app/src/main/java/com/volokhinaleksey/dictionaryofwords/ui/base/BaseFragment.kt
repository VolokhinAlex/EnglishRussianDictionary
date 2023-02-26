package com.volokhinaleksey.dictionaryofwords.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.volokhinaleksey.dictionaryofwords.utils.AndroidNetworkStatus
import com.volokhinaleksey.dictionaryofwords.utils.NetworkStatus
import com.volokhinaleksey.dictionaryofwords.viewmodel.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment<T : Any> : Fragment() {
    abstract val viewModel: BaseViewModel<T>
    abstract fun renderData(state: T)

    protected var isNetworkAvailable: Boolean = false
    private var networkStatus: NetworkStatus? = null

    protected val compositeDisposable = CompositeDisposable()

    abstract fun showViewOnLoading()

    abstract fun showViewOnError(error: String)

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