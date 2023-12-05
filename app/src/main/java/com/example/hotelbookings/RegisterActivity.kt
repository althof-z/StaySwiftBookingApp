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

class RegisterActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Intent Ke Login Jika Sudah Ada Akun
        val textViewLoginHere: TextView = findViewById(R.id.LoginText)
        textViewLoginHere.setOnClickListener {
            // Lakukan intent ke halaman login Kotlin di sini
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        //--------------------------------------------------------------------------------------//
        dbHelper = DatabaseHelper(this)

        usernameEditText = findViewById(R.id.isiUsername)
        emailEditText = findViewById(R.id.isiEmail)
        passwordEditText = findViewById(R.id.isiPassword)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val success = dbHelper.addUser(username, email, password)

            if (success != -1L) {
                // Jika pendaftaran berhasil, munculkan AlertDialog
                showSuccessDialog()
            } else {
                // Jika pendaftaran gagal, munculkan AlertDialog
                showFailureDialog()
            }
        }

    }

    private fun showSuccessDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registration Successful")
        builder.setMessage("Your account has been successfully registered.")

        builder.setPositiveButton("OK") { dialog, which ->
            // Intent ke halaman login
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun showFailureDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registration Failed")
        builder.setMessage("Please make sure to fill in all the fields correctly.")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Dismiss the dialog
        })

        val dialog = builder.create()
        dialog.show()
    }

}