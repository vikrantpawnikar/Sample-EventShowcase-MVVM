package com.vikrant.eventshowcase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.vikrant.event.R
import com.vikrant.event.databinding.EventListActivityBinding

class EventListMainActivity : AppCompatActivity() {

    private lateinit var binding: EventListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EventListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.event_navigation))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}