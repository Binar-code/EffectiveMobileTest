package com.example.effectivemobiletest.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobiletest.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host)
                as NavHostFragment).navController

        val bottomBar = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomBar)

        val bottomException = setOf(
            R.id.loginFragment
        )

        navController.addOnDestinationChangedListener { _, dest, _ ->
            bottomBar.isVisible = dest.id !in bottomException
        }

        bottomBar.setupWithNavController(navController)
    }
}