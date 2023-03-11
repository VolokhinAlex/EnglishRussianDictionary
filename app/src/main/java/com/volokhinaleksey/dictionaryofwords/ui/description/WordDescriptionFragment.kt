package com.volokhinaleksey.dictionaryofwords.ui.description

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentWordDescriptionBinding
import com.volokhinaleksey.dictionaryofwords.states.MeaningsState
import com.volokhinaleksey.dictionaryofwords.ui.base.BaseFragment
import com.volokhinaleksey.dictionaryofwords.viewmodel.WordDescriptionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class WordDescriptionFragment : BaseFragment<MeaningsState>(), TextToSpeech.OnInitListener {

    private var _binding: FragmentWordDescriptionBinding? = null
    private val binding get() = _binding!!

    private var textToSpeech: TextToSpeech? = null

    override val viewModel: WordDescriptionViewModel by viewModel()

    private val wordData by navArgs<WordDescriptionFragmentArgs>()

    private val wordExamplesAdapter: WordExamplesAdapter by lazy { WordExamplesAdapter() }

    private val similarTranslationsAdapter: SimilarTranslationsAdapter by lazy { SimilarTranslationsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordDescriptionBinding.inflate(inflater)
        textToSpeech = TextToSpeech(requireContext(), this)
        binding.backArrow.setOnClickListener { requireView().findNavController().popBackStack() }
        viewModel.currentData.observe(viewLifecycleOwner) { renderData(state = it) }
        viewModel.getMeanings(
            meaningId = wordData.wordData.meanings?.get(0)?.id ?: 0,
            isOnline = isNetworkAvailable
        )
        initLists()
        return binding.root
    }

    private fun initLists() {
        binding.examplesList.adapter = wordExamplesAdapter
        binding.examplesList.layoutManager = LinearLayoutManager(requireContext())
        binding.similarTranslation.adapter = similarTranslationsAdapter
        binding.similarTranslation.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                error("This language is not supported")
            }
        } else {
            error("Some mistake has occurred")
        }
    }

    override fun renderData(state: MeaningsState) {
        when (state) {
            is MeaningsState.Error -> showViewOnError(error = state.error.localizedMessage.orEmpty())
            MeaningsState.Loading -> showViewOnLoading()
            is MeaningsState.Success -> {
                val meaningsData = state.wordData[0]
                showViewOnSuccess()
                binding.textSpeech.setOnClickListener {
                    textToSpeech?.speak(
                        meaningsData.text,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        "textToSpeech"
                    )
                }
                binding.word.text = meaningsData.text
                binding.transcription.text = meaningsData.transcription
                binding.translation.text = meaningsData.translation?.translation
                meaningsData.examples?.let { wordExamplesAdapter.submitList(it) }
                meaningsData.similarTranslation?.let { similarTranslationsAdapter.submitList(it) }
            }
        }
    }

    override fun showViewOnLoading() {
        binding.baseView.errorMessage.visibility = View.GONE
        binding.appBar.visibility = View.GONE
        binding.contentContainer.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.VISIBLE
    }

    override fun showViewOnError(error: String) {
        binding.appBar.visibility = View.GONE
        binding.contentContainer.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.errorMessage.visibility = View.VISIBLE
        binding.baseView.reloadButton.visibility = View.VISIBLE
        binding.baseView.errorMessage.text = error
    }

    override fun showViewOnSuccess() {
        binding.appBar.visibility = View.VISIBLE
        binding.contentContainer.visibility = View.VISIBLE
        binding.baseView.errorMessage.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
    }

    override fun onDestroy() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        super.onDestroy()
    }

}