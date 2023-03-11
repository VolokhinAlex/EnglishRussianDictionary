package com.volokhinaleksey.core.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.volokhinaleksey.core.R
import com.volokhinaleksey.core.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationBar.setupWithNavController(navController = navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.description_nav_graph) {
                binding.bottomNavigationBar.visibility = View.GONE
            } else {
                binding.bottomNavigationBar.visibility = View.VISIBLE
            }
        }
    }

}