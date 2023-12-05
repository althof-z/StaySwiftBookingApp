package com.example.hotelbookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Find the ImageView for the logout button
        val logoutImageView = findViewById<ImageView>(R.id.logoutImageView)

        // Set OnClickListener for the logout ImageView
        logoutImageView.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("Yes") { dialog, which ->
            logout()
        }
        builder.setNegativeButton("No") { dialog, which ->
            // Do nothing, close the dialog
        }
        builder.show()
    }

    private fun logout() {
        // Perform logout actions, clear sessions, etc.

        // Start the LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        // Clear the back stack so that the user cannot navigate back to HomeActivity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }


}