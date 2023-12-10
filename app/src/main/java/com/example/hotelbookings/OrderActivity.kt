package com.example.hotelbookings
import BookingDatabaseHelper
import DatabaseHelper
import DatabaseHelper.Companion.COLUMN_USERNAME
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookings.adapter.BookingAdapter
import com.example.hotelbookings.adapter.UserAdapter
import com.example.hotelbookings.data.Booking
import com.example.hotelbookings.data.User

class OrderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Find the RecyclerView
        recyclerView = findViewById(R.id.bookingRecyclerView) //PR

        // Set up the LinearLayoutManager for the RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // Gantilah "getUserListFromDatabase()" dengan metode sesuai kebutuhan Anda
        val userList = getUserListFromDatabase()

        userAdapter = UserAdapter(userList)
        recyclerView.adapter = userAdapter

        //==================================================================//


        //=================================================================//

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

        //=====================================================================================//

        //Read from DB

        // Assume you have a list of bookings (replace with your actual data source)
        val bookingList = retrieveBookingData() // Replace with your data retrieval logic

        // Initialize the adapter and set it to the RecyclerView
        val bookingAdapter = BookingAdapter(bookingList)
        recyclerView.adapter = bookingAdapter

        //=====================================================================================//

        //Delete from DB
        bookingAdapter.setOnItemClickListener(object : BookingAdapter.OnItemClickListener {
            override fun onItemClick(bookingId: Long) {
                // Create an AlertDialog
                val alertDialogBuilder = AlertDialog.Builder(this@OrderActivity)
                alertDialogBuilder.setTitle("Delete Booking")
                alertDialogBuilder.setMessage("Are you sure you want to delete this booking?")

                // Set positive button action
                alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
                    val bookingDbHelper = BookingDatabaseHelper(this@OrderActivity)

                    // Call the deleteBooking function
                    val isDeleted = bookingDbHelper.deleteBooking(bookingId)

                    if (isDeleted) {
                        // Booking successfully deleted
                        Toast.makeText(this@OrderActivity, "Booking deleted successfully", Toast.LENGTH_SHORT).show()

                        // Optionally, you can update your RecyclerView or UI to reflect the changes
                        val updatedBookingList = retrieveBookingData() // Replace with your data retrieval logic
                        bookingAdapter.updateData(updatedBookingList)
                        // Update the RecyclerView with the new data
                    } else {
                        // Failed to delete booking
                        Toast.makeText(this@OrderActivity, "Failed to delete booking", Toast.LENGTH_SHORT).show()
                    }
                }

                // Set negative button action
                alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss() // Dismiss the dialog if "No" is clicked
                }

                // Show the AlertDialog
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        })


    }

    private fun getUserListFromDatabase(): List<User> {
        val dbHelper = DatabaseHelper(this)
        val userList = mutableListOf<User>()

        // Gantilah query dan kolom sesuai dengan struktur database Anda
        val cursor = dbHelper.readableDatabase.query(
            DatabaseHelper.TABLE_USER,
            arrayOf(DatabaseHelper.COLUMN_USERNAME, DatabaseHelper.COLUMN_EMAIL),
            null,
            null,
            null,
            null,
            null
        )

        // Ensure cursor is not null
        cursor?.use {
            // Check if the columns exist in the cursor
            val usernameIndex = it.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)
            val emailIndex = it.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)

            if (usernameIndex != -1 && emailIndex != -1) {
                while (it.moveToNext()) {
                    val username = it.getString(usernameIndex)
                    val email = it.getString(emailIndex)
                    userList.add(User(username, email))
                }
            } else {
                // Handle the case where columns are not found
                // Log an error, show a message, or take appropriate action
            }
        }


        return userList
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

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        // Add your custom logic here, or call super.onBackPressed() to close the activity
        super.onBackPressed()
    }

    //Read From DB
    // Example function to retrieve booking data (replace with your actual logic)
    private fun retrieveBookingData(): List<Booking> {
        // Query your database or fetch data from another source
        // Return a list of Booking objects
        val bookingDbHelper = BookingDatabaseHelper(this)
        return bookingDbHelper.getAllBookings()
    }
}
