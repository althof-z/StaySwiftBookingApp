package com.example.hotelbookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.hotelbookings.data.DataHotel

class HotelReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_reservation)

        val backButton = findViewById<ImageView>(R.id.arrowBack)
        val backButtonHome = findViewById<ImageView>(R.id.HomeImageView)
        backButton.setOnClickListener {
            // Panggil onBackPressed untuk kembali ke activity sebelumnya
            onBackPressed()
        }
        backButtonHome.setOnClickListener {
            // Panggil onBackPressed untuk kembali ke activity sebelumnya
            onBackPressed()
        }

        val selectedHotel = intent.getParcelableExtra<DataHotel>("selectedHotel")

        // Menampilkan deskripsi pada tampilan yang sesuai (misalnya, TextView)
        val descriptionTextView = findViewById<TextView>(R.id.detailHotelDescriptionTextView)
        descriptionTextView.text = selectedHotel?.description

        // Set data hotel ke tampilan detail
        val detailImageView: ImageView = findViewById(R.id.detailImageView)
        val hotelNameTextView: TextView = findViewById(R.id.detailHotelNameTextView)
        val hotelLocationTextView: TextView = findViewById(R.id.detailHotelLocationTextView)
        val hotelPriceTextView: TextView = findViewById(R.id.detailHotelPriceTextView)
        val hotelDescriptionTextView: TextView = findViewById(R.id.detailHotelDescriptionTextView)

        selectedHotel?.let {
            detailImageView.setImageResource(it.photo)
            hotelNameTextView.text = it.name
            hotelLocationTextView.text = it.location
            hotelPriceTextView.text = it.price
            hotelDescriptionTextView.text = it.description
        }

    }
}