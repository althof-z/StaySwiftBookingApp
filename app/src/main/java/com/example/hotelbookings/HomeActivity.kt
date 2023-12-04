package com.example.hotelbookings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //....

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    // Handle click on Home icon
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_exit -> {
                    // Handle click on Exit icon
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    }
}