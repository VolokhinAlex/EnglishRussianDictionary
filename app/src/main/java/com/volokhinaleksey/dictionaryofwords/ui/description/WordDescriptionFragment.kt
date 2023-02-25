package com.volokhinaleksey.dictionaryofwords.ui.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentWordDescriptionBinding


class WordDescriptionFragment : Fragment() {

    private var _binding: FragmentWordDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordDescriptionBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = WordDescriptionFragment()
    }
}