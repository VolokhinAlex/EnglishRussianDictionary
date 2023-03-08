package com.volokhinaleksey.core.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.volokhinaleksey.core.R
import com.volokhinaleksey.core.databinding.ActivityMainBinding

/**
 * The main activity for including fragment-based screens in it
 */

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Get NavHostFragment to get NavController and provide navigation using bottom navigation view
         */

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationBar.setupWithNavController(navController = navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            /**
             * Checking destination if you need to hide the bottom navigation view on some screens
             */

            if (destination.id == R.id.description_nav_graph) {
                binding.bottomNavigationBar.visibility = View.GONE
            } else {
                binding.bottomNavigationBar.visibility = View.VISIBLE
            }
        }
    }

}