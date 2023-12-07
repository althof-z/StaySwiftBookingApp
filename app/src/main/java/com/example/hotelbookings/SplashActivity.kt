package com.example.hotelbookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000 // 2 seconds delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app's main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Close this activity
            finish()
        }, SPLASH_TIME_OUT)

    }
}