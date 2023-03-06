package com.volokhinaleksey.dictionaryofwords.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volokhinaleksey.dictionaryofwords.databinding.FragmentHistorySearchBinding

class HistorySearchFragment : Fragment() {

    private var _binding: FragmentHistorySearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistorySearchBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistorySearchFragment()
    }
}