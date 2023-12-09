package com.example.hotelbookings

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Initialize EditText fields
        dateCheckInEditText = findViewById(R.id.dateCheckIn)
        dateCheckOutEditText = findViewById(R.id.dateCheckOut)
        adultNumEditText = findViewById(R.id.adultNum)
        childrenNumEditText = findViewById(R.id.childrenNum)
        commentsEditText = findViewById(R.id.commentsText)

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
    }
}