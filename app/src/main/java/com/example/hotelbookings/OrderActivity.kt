package com.example.hotelbookings

import DatabaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text

class OrderActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var bookingId: TextView
    private lateinit var hotelName: TextView
    private lateinit var roomType: TextView
    private lateinit var checkinOut: TextView
    private lateinit var price: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Intent Navigation Bottom
        val logoutImageView = findViewById<ImageView>(R.id.logoutImageView)
        val backButton: ImageView = findViewById(R.id.imageView3)

        // Set OnClickListener for the logout ImageView
        logoutImageView.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        backButton.setOnClickListener {
            // Handle the back button click
            onBackPressed()
        }

        bookingId = findViewById(R.id.bookingId)
        hotelName = findViewById(R.id.hotelNameTextView)
        roomType = findViewById(R.id.roomTypeTextView)
        checkinOut = findViewById(R.id.checkinOut)
        price = findViewById(R.id.priceTextView)

        dbHelper = DatabaseHelper(this)

        val allBookingInfo = dbHelper.getAllBookingInfo()

        for (bookingInfo in allBookingInfo) {
            bookingId.append("ID: ${bookingInfo.bookingId}")
            hotelName.append("Hotel: ${bookingInfo.hotelName}")
            roomType.append("Room Type: ${bookingInfo.roomType}")
            checkinOut.append("Checkin/Out: ${bookingInfo.dateCheckIn} / ${bookingInfo.dateCheckOut}")
            price.append("Rp.${bookingInfo.price}")
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

    private fun navigateToHomeActivity() {
        // Start the HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        // Add your custom logic here, or call super.onBackPressed() to close the activity
        super.onBackPressed()
    }

}