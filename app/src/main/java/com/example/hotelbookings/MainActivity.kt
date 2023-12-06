package com.example.hotelbookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    private val imageResources = listOf(
        R.drawable.fourseason,
        R.drawable.asyanakemayoran,
        R.drawable.aryadutamenteng
        // Add more image resources as needed
    )

    private lateinit var viewPager: ViewPager
    private lateinit var slideAdapter: SlideAdapter

    private val handler = Handler()

    private val runnable = object : Runnable {
        override fun run() {
            var currentItem = viewPager.currentItem
            currentItem = if (currentItem < imageResources.size - 1) {
                currentItem + 1
            } else {
                0
            }
            viewPager.currentItem = currentItem
            handler.postDelayed(this, 4000) // Change the duration as needed (in milliseconds)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        slideAdapter = SlideAdapter(this, imageResources)
        viewPager.adapter = slideAdapter

        // Start the slideshow
        handler.postDelayed(runnable, 4000) // Initial delay (in milliseconds)

        //Intent Login
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            // Create an Intent to navigate to the LoginActivity
            val intent = Intent(this@MainActivity, LoginActivity::class.java)

            // Start the LoginActivity
            startActivity(intent)
        }

        //Intent Register
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            // Create an Intent to navigate to the RegisterActivity
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)

            // Start the LoginActivity
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}
