package com.volokhinaleksey.description.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.volokhinaleksey.core.ui.base.BaseFragment
import com.volokhinaleksey.core.ui.parcelable
import com.volokhinaleksey.description.databinding.FragmentWordDescriptionBinding
import com.volokhinaleksey.description.viewmodel.WordDescriptionViewModel
import com.volokhinaleksey.models.states.FavoriteState
import com.volokhinaleksey.models.states.MeaningsState
import com.volokhinaleksey.models.ui.FavoriteWord
import com.volokhinaleksey.models.ui.Word
import kotlinx.coroutines.launch
import org.koin.android.scope.getOrCreateScope
import java.util.Locale

class WordDescriptionFragment : BaseFragment<MeaningsState>(), TextToSpeech.OnInitListener {

    private var _binding: FragmentWordDescriptionBinding? = null
    private val binding get() = _binding!!

    private val scope = getOrCreateScope().value

    private var textToSpeech: TextToSpeech? = null

    override val viewModel: WordDescriptionViewModel = scope.get()

    private val wordData: Word? by lazy { arguments?.parcelable() }

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
        viewModel.getFavoriteWord(wordId = wordData?.id ?: 0)
        viewModel.favoriteData.observe(viewLifecycleOwner) {
            renderFavoriteData(state = it)
        }
        binding.addFavorite.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveFavoriteWord(
                word = FavoriteWord(
                    wordId = wordData?.id ?: 0,
                    word = wordData?.word.orEmpty(),
                    isFavorite = isChecked,
                    meanings = wordData?.meanings.orEmpty()
                )
            )
        }
        lifecycleScope.launch {
            viewModel.getMeanings(
                meaningId = wordData?.meanings?.get(0)?.id ?: 0,
                isOnline = isNetworkAvailable
            )
        }
        initLists()
        return binding.root
    }

    private fun renderFavoriteData(state: FavoriteState) {
        when (state) {
            is FavoriteState.Error -> showViewOnError(error = state.error.localizedMessage.orEmpty())
            FavoriteState.Loading -> showViewOnLoading()
            is FavoriteState.Success -> {
                val data = state.favoriteWord
                showViewOnSuccess()
                if (data.isEmpty()) {
                    viewModel.saveFavoriteWord(
                        FavoriteWord(
                            wordId = wordData?.id ?: 0,
                            word = wordData?.word.orEmpty(),
                            isFavorite = false,
                            meanings = emptyList()
                        )
                    )
                } else {
                    binding.addFavorite.isChecked = data[0].isFavorite
                }
            }
        }
    }

    /**
     * Method for initializing the list.
     */

    private fun initLists() {
        binding.examplesList.adapter = wordExamplesAdapter
        binding.examplesList.layoutManager = LinearLayoutManager(requireContext())
        binding.similarTranslation.adapter = similarTranslationsAdapter
        binding.similarTranslation.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    /**
     * A method for initializing a Text To Speech object in which initial parameters are set during creation
     */

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

    /**
     * Method of processing states of the [MeaningsState] class coming from outside
     */

    override fun renderData(state: MeaningsState) {
        when (state) {
            is MeaningsState.Error -> showViewOnError(error = state.error.localizedMessage.orEmpty())
            MeaningsState.Loading -> showViewOnLoading()
            is MeaningsState.Success -> {
                val meaningsData = state.wordData[0]
                showViewOnSuccess()
                binding.textSpeech.setOnClickListener {
                    textToSpeech?.speak(
                        meaningsData.word,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        "textToSpeech"
                    )
                }
                binding.word.text = meaningsData.word.ifBlank { wordData?.word }
                binding.transcription.text = meaningsData.transcription
                binding.translation.text = meaningsData.translation.translation
                meaningsData.examples.let { wordExamplesAdapter.submitList(it) }
                meaningsData.similarTranslation.let { similarTranslationsAdapter.submitList(it) }
            }
        }
    }

    /**
     * Method for showing the loading status
     */

    override fun showViewOnLoading() {
        binding.baseView.errorMessage.visibility = View.GONE
        binding.appBar.visibility = View.GONE
        binding.contentContainer.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.VISIBLE
    }

    /**
     * Method for showing the error status
     */

    override fun showViewOnError(error: String) {
        binding.appBar.visibility = View.GONE
        binding.contentContainer.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.errorMessage.visibility = View.VISIBLE
        binding.baseView.reloadButton.visibility = View.VISIBLE
        binding.baseView.errorMessage.text = error
    }

    /**
     * Method for showing the success status
     */

    override fun showViewOnSuccess() {
        binding.appBar.visibility = View.VISIBLE
        binding.contentContainer.visibility = View.VISIBLE
        binding.baseView.errorMessage.visibility = View.GONE
        binding.baseView.progressBar.visibility = View.GONE
        binding.baseView.reloadButton.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        scope.close()
    }

}