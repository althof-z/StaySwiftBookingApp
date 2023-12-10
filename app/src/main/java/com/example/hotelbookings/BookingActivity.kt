package com.example.hotelbookings

import BookingDatabaseHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.squareup.picasso.Picasso

class BookingActivity : AppCompatActivity() {
    // Add these properties
    private lateinit var dateCheckInEditText: EditText
    private lateinit var dateCheckOutEditText: EditText
    private lateinit var adultNumEditText: EditText
    private lateinit var childrenNumEditText: EditText
    private lateinit var commentsEditText: EditText
    private lateinit var reserverNameEditText: EditText

    //For Insert Into DB
    private lateinit var bookingDbHelper: BookingDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Initialize EditText fields
        dateCheckInEditText = findViewById(R.id.dateCheckIn)
        dateCheckOutEditText = findViewById(R.id.dateCheckOut)
        adultNumEditText = findViewById(R.id.adultNum)
        childrenNumEditText = findViewById(R.id.childrenNum)
        commentsEditText = findViewById(R.id.commentsText)
        reserverNameEditText = findViewById(R.id.textView12)  // Initialize the new EditText

        // Retrieve data from intent
        val selectedRoomName = intent.getStringExtra("selectedRoomName")
        val selectedRoomImage = intent.getIntExtra("selectedRoomImage", 0)
        val selectedRoomHotelName = intent.getStringExtra("selectedHotelName")
        val selectedRoomHotelLocatiom = intent.getStringExtra("selectedHotelLocation")

        // Assume you have TextViews in your layout to display the room information
        val roomNameTextView = findViewById<TextView>(R.id.roomNameTextView)
        val roomImageView = findViewById<ImageView>(R.id.roomImageView)
        val roomHotelNameTextView = findViewById<TextView>(R.id.hotelNameTextView)
        val roomHotelLocationTextView = findViewById<TextView>(R.id.hotelLocationTextView)

        // Set the retrieved data to the TextViews
        roomNameTextView.text = selectedRoomName
        roomImageView.setImageResource(selectedRoomImage)
        roomHotelNameTextView.text = selectedRoomHotelName
        roomHotelLocationTextView.text = selectedRoomHotelLocatiom

        Picasso.get().load(selectedRoomImage).into(roomImageView)

        val backButton= findViewById<ImageView>(R.id.imageBackButton)
        val backButtonHome = findViewById<ImageView>(R.id.HomeImageView)
        backButton.setOnClickListener {
            // Panggil onBackPressed untuk kembali ke activity sebelumnya
            onBackPressed()
        }
        backButtonHome.setOnClickListener {
            // Panggil onBackPressed untuk kembali ke activity sebelumnya
            onBackPressed()
        }

        //======================================================================================//

        //Insert Into DB

        val dateCheckInEditText = findViewById<EditText>(R.id.dateCheckIn)
        val dateCheckOutEditText = findViewById<EditText>(R.id.dateCheckOut)
        val adultNumEditText = findViewById<EditText>(R.id.adultNum)
        val childrenNumEditText = findViewById<EditText>(R.id.childrenNum)
        val commentTextEditText = findViewById<EditText>(R.id.commentsText)
        val reserverNameEditText = findViewById<EditText>(R.id.textView12)

        // Initialize BookingDatabaseHelper
        bookingDbHelper = BookingDatabaseHelper(this)

        // Set up click listener for the button
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // Get values from the EditText and TextView elements
            val dateCheckIn = dateCheckInEditText.text.toString()
            val dateCheckOut = dateCheckOutEditText.text.toString()
            val adultNum = adultNumEditText.text.toString().toIntOrNull() ?: 0
            val childrenNum = childrenNumEditText.text.toString().toIntOrNull() ?: 0
            val commentsText = commentTextEditText.text.toString()
            val reserverName = reserverNameEditText.text.toString()  // Retrieve reserverName from the new EditText

            // Call the insertBooking function
            val newRowId = bookingDbHelper.insertBooking(dateCheckIn, dateCheckOut, adultNum, childrenNum, commentsText, reserverName)

            if (newRowId != -1L) {
                // Booking successfully inserted
                Toast.makeText(this, "Booking added successfully", Toast.LENGTH_SHORT).show()
            } else {
                // Failed to insert booking
                Toast.makeText(this, "Failed to add booking", Toast.LENGTH_SHORT).show()
            }
        }

        //==========================================================================================//
    }
}