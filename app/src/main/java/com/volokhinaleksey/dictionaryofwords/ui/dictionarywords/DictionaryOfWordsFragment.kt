package com.volokhinaleksey.dictionaryofwords.ui.dictionarywords

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.volokhinaleksey.dictionaryofwords.R
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentDictionaryOfWordsBinding
import com.volokhinaleksey.dictionaryofwords.schedulers.SchedulersProvider
import com.volokhinaleksey.dictionaryofwords.schedulers.SchedulersProviderImpl
import com.volokhinaleksey.dictionaryofwords.states.WordsState
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseFragment
import com.volokhinaleksey.dictionaryofwords.ui.imageloaders.ImageLoader
import com.volokhinaleksey.dictionaryofwords.viewmodel.DictionaryOfWordsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.concurrent.TimeUnit

class DictionaryOfWordsFragment : BaseFragment<WordsState>(), TextToSpeech.OnInitListener {

    private var _binding: FragmentDictionaryOfWordsBinding? = null
    private val binding: FragmentDictionaryOfWordsBinding get() = _binding!!

    private var schedulers: SchedulersProvider = SchedulersProviderImpl()

    private val imageLoader: ImageLoader<ImageView> by inject()

    private var textToSpeech: TextToSpeech? = null

    private val dictionaryOfWordsAdapter: DictionaryOfWordsAdapter by lazy {
        DictionaryOfWordsAdapter(imageLoader = imageLoader, onSoundClickListener = {
            textToSpeech?.speak(it, TextToSpeech.QUEUE_FLUSH, null, "textToSpeech")
        }) {
            requireView().findNavController().navigate(R.id.wordDescriptionFragment)
        }
    }

    override val viewModel: DictionaryOfWordsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        textToSpeech = TextToSpeech(requireContext(), this)
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

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val locale = Locale("ru")
            val result = textToSpeech!!.setLanguage(locale)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                error("This language is not supported")
            }
        } else {
            error("Some mistake has occurred")
        }
    }

    override fun onDestroy() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = DictionaryOfWordsFragment()
    }
}