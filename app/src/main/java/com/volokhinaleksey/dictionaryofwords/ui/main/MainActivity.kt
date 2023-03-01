package com.volokhinaleksey.dictionaryofwords.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.volokhinaleksey.dictionaryofwords.databinding.ActivityMainBinding
import com.volokhinaleksey.dictionaryofwords.ui.dictionarywords.DictionaryOfWordsFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, DictionaryOfWordsFragment.newInstance()).commit()
    }

}