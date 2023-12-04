package com.example.hotelbookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Intent Ke Register Jika Belum Memiliki Akun
        val textViewLoginHere: TextView = findViewById(R.id.LoginText)
        textViewLoginHere.setOnClickListener {
            // Lakukan intent ke halaman Register Kotlin di sini
            val intent = Intent(this@LoginActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}