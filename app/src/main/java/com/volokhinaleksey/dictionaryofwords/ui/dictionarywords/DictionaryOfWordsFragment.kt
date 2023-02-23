package com.volokhinaleksey.dictionaryofwords.ui.dictionarywords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentDictionaryOfWordsBinding
import com.volokhinaleksey.dictionaryofwords.schedulers.SchedulersProvider
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseFragment
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.ImageLoader
import com.volokhinaleksey.dictionaryofwords.viewmodel.DictionaryOfWordsViewModel
import dagger.android.support.AndroidSupportInjection
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DictionaryOfWordsFragment : BaseFragment<WordsState>() {

    private var _binding: FragmentDictionaryOfWordsBinding? = null
    private val binding: FragmentDictionaryOfWordsBinding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var schedulers: SchedulersProvider

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>

    private val dictionaryOfWordsAdapter: DictionaryOfWordsAdapter by lazy {
        DictionaryOfWordsAdapter(imageLoader = imageLoader)
    }

    override val viewModel: DictionaryOfWordsViewModel by lazy {
        viewModelFactory.create(DictionaryOfWordsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDictionaryOfWordsBinding.inflate(inflater)
        binding.wordsList.layoutManager = LinearLayoutManager(requireContext())
        binding.wordsList.adapter = dictionaryOfWordsAdapter
        viewModel.currentData.observe(viewLifecycleOwner) { renderData(it) }
        compositeDisposable.add(
            binding.searchEditText
                .textChanges()
                .subscribeOn(schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(schedulers.mainThread())
                .subscribe {
                    if (it.isNotEmpty()) {
                        viewModel.getWordMeanings(
                            word = it.toString(),
                            isOnline = isNetworkAvailable
                        )
                    }
                }
        )
        return binding.root
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DictionaryOfWordsFragment()
    }
}