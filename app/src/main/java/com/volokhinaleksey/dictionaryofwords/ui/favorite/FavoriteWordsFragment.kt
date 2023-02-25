package com.volokhinaleksey.dictionaryofwords.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentFavoriteWordsBinding

class FavoriteWordsFragment : Fragment() {

    private var _binding: FragmentFavoriteWordsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteWordsBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteWordsFragment()
    }
}