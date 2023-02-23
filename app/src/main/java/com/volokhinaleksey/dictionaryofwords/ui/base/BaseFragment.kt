package com.volokhinaleksey.dictionaryofwords.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.utils.AndroidNetworkStatus
import com.volokhinaleksey.dictionaryofwords.utils.NetworkStatus
import com.volokhinaleksey.dictionaryofwords.viewmodel.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment<T : WordsState> : Fragment() {
    abstract val viewModel: BaseViewModel<T>
    abstract fun renderData(wordsState: WordsState)
    protected var isNetworkAvailable: Boolean = false
    private var networkStatus: NetworkStatus? = null

    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //isNetworkAvailable = isNetworkAvailable(requireContext())
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