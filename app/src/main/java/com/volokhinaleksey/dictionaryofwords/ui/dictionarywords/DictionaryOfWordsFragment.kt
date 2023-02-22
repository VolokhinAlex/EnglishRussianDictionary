package com.volokhinaleksey.dictionaryofwords.ui.dictionarywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentDictionaryOfWordsBinding
import com.volokhinaleksey.dictionaryofwords.presenter.Presenter
import com.volokhinaleksey.dictionaryofwords.repository.DictionaryApiHolder
import com.volokhinaleksey.dictionaryofwords.repository.DictionaryOfWordsRepository
import com.volokhinaleksey.dictionaryofwords.schedulers.SchedulersProviderImpl
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseFragment
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseView
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.CoilImageLoader
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class DictionaryOfWordsFragment : BaseFragment() {

    private var _binding: FragmentDictionaryOfWordsBinding? = null
    private val binding: FragmentDictionaryOfWordsBinding get() = _binding!!

    private val schedulers = SchedulersProviderImpl()

    private val dictionaryOfWordsPresenter by lazy {
        DictionaryOfWordsPresenter(
            mainInteractor = MainInteractor(
                remoteRepository = DictionaryOfWordsRepository(
                    DictionaryApiHolder
                ), localRepository = DictionaryOfWordsRepository(DictionaryApiHolder)
            ),
            schedulers = schedulers
        )
    }

    private val dictionaryOfWordsAdapter: DictionaryOfWordsAdapter by lazy {
        DictionaryOfWordsAdapter(imageLoader = CoilImageLoader())
    }

    override fun createPresenter(): Presenter<WordsState, BaseView> =
        dictionaryOfWordsPresenter

    override fun renderData(wordsState: WordsState) {
        when (wordsState) {
            is WordsState.Error -> showViewOnError(error = wordsState.error.localizedMessage.orEmpty())
            WordsState.Loading -> showViewOnLoading()
            is WordsState.Success -> {
                val words = wordsState.wordData
                showViewOnSuccess()
                dictionaryOfWordsAdapter.submitList(words)
            }
        }
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDictionaryOfWordsBinding.inflate(inflater)
        binding.wordsList.layoutManager = LinearLayoutManager(requireContext())
        binding.wordsList.adapter = dictionaryOfWordsAdapter

        compositeDisposable.add(
            binding.searchEditText
                .textChanges()
                .subscribeOn(schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(schedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        presenter?.getWordsData(it.toString(), true)
                    }
                }
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DictionaryOfWordsFragment()
    }

    private fun showViewOnLoading() {
        binding.errorMessage.visibility = View.GONE
        binding.wordsList.visibility = View.GONE
        binding.reloadButton.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showViewOnError(error: String) {
        binding.wordsList.visibility = View.GONE
        binding.progressBar.visibility = View.GONE

        binding.errorMessage.visibility = View.VISIBLE
        binding.reloadButton.visibility = View.VISIBLE
        binding.errorMessage.text = error
    }

    private fun showViewOnSuccess() {
        binding.wordsList.visibility = View.VISIBLE

        binding.errorMessage.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.reloadButton.visibility = View.GONE
    }
}