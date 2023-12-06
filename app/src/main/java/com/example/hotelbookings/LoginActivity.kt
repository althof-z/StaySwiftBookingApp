package com.example.hotelbookings

import DatabaseHelper
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerText: TextView
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Intent Ke Register Jika Belum Memiliki Akun
        val textViewLoginHere: TextView = findViewById(R.id.RegisterText)
        textViewLoginHere.setOnClickListener {
            // Lakukan intent ke halaman Register Kotlin di sini
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        //----------------------------------------------------------------------------------//
        dbHelper = DatabaseHelper(this)

        emailEditText = findViewById(R.id.isiEmail)
        passwordEditText = findViewById(R.id.isiPassword)
        loginButton = findViewById(R.id.Loginbutton)
        registerText = findViewById(R.id.RegisterText)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val user = dbHelper.getUserByEmailAndPassword(email, password)

            if (dbHelper.checkUser(email, password) && user != null) {
                // Jika login berhasil
                showSuccessDialog(user.username)
            } else {
                // Jika login gagal
                showFailureDialog()
            }
        }

        registerText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showSuccessDialog(username: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Login Successful")
        builder.setMessage("You have successfully logged in.")

        builder.setPositiveButton("OK") { dialog, which ->
            // Intent ke halaman home atau halaman lainnya
            val intent = Intent(this@LoginActivity, HomeActivity::class.java).apply {
                putExtra("USERNAME", username)
            }
            startActivity(intent)
            finish()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun showFailureDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Login Failed")
        builder.setMessage("Invalid email or password. Please try again.")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Dismiss the dialog
        })

        val dialog = builder.create()
        dialog.show()
    }

}