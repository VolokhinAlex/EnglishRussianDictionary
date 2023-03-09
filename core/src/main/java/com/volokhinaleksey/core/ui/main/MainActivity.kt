package com.volokhinaleksey.core.ui.main

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.volokhinaleksey.core.R
import com.volokhinaleksey.core.databinding.ActivityMainBinding
import com.volokhinaleksey.viewbyidutils.viewById
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        var isHideSplashScreen = false
        lifecycleScope.launch {
            delay(3000)
            isHideSplashScreen = true
        }
        val content by viewById<View>(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                return if (isHideSplashScreen) {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    true
                } else {
                    false
                }
            }
        })

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

            if (destination.parent?.id == R.id.description_nav_graph) {
                binding.bottomNavigationBar.visibility = View.GONE
            } else {
                binding.bottomNavigationBar.visibility = View.VISIBLE
            }
        }
    }

}