package com.example.hotelbookings
import DatabaseHelper
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookings.adapter.UserAdapter
import com.example.hotelbookings.data.User

class OrderActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        recyclerView = findViewById(R.id.bookingRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Gantilah "getUserListFromDatabase()" dengan metode sesuai kebutuhan Anda
        val userList = getUserListFromDatabase()

        userAdapter = UserAdapter(userList)
        recyclerView.adapter = userAdapter

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
    }

    private fun getUserListFromDatabase(): List<User> {
        val dbHelper = DatabaseHelper(this)
        val cursor = dbHelper.readableDatabase.query(
            DatabaseHelper.TABLE_USER,
            arrayOf(DatabaseHelper.COLUMN_USERNAME, DatabaseHelper.COLUMN_EMAIL),
            null,
            null,
            null,
            null,
            null
        )

        val userList = mutableListOf<User>()

        if (cursor != null) {
            userList.addAll(getUserListFromCursor(cursor))
            cursor.close()
        }

        return userList
    }

    private fun getUserListFromCursor(cursor: Cursor): List<User> {
        val userList = mutableListOf<User>()

        if (cursor.moveToFirst()) {
            val usernameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME)
            val emailIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)

            // Check if the columns are found in the cursor
            if (usernameIndex != -1 && emailIndex != -1) {
                do {
                    val username = cursor.getString(usernameIndex)
                    val email = cursor.getString(emailIndex)
                    userList.add(User(username, email))
                } while (cursor.moveToNext())
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
}
