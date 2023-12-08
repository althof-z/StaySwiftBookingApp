package com.example.hotelbookings

import DatabaseHelper
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class BookingActivity : AppCompatActivity() {
    //Db
    private lateinit var hotelNameTextView: TextView
    private lateinit var roomNameTextView: TextView
    private lateinit var dateCheckInEditText: EditText
    private lateinit var dateCheckOutEditText: EditText
    private lateinit var adultNumEditText: EditText
    private lateinit var childrenNumEditText: EditText
    private lateinit var commentsEditText: EditText
    private lateinit var priceTextView: TextView
    private lateinit var bookingButton: Button
    private lateinit var dbHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Retrieve data from intent
        val selectedRoomName = intent.getStringExtra("selectedRoomName")
        val selectedRoomImage = intent.getIntExtra("selectedRoomImage", 0)
        val selectedRoomHotelName = intent.getStringExtra("selectedHotelName")
        val selectedRoomHotelLocatiom = intent.getStringExtra("selectedHotelLocation")
        val selectedRoomHotelPrice = intent.getStringExtra("selectedRoomHotelPrice")

        // Assume you have TextViews in your layout to display the room information
        roomNameTextView = findViewById(R.id.roomNameTextView)
        val roomImageView = findViewById<ImageView>(R.id.roomImageView)
        hotelNameTextView = findViewById(R.id.hotelNameTextView)
        val roomHotelLocationTextView = findViewById<TextView>(R.id.hotelLocationTextView)
        priceTextView = findViewById(R.id.hotelRoomPriceTextView)

        // Set the retrieved data to the TextViews
        roomNameTextView.text = selectedRoomName
        roomImageView.setImageResource(selectedRoomImage)
        hotelNameTextView.text = selectedRoomHotelName
        roomHotelLocationTextView.text = selectedRoomHotelLocatiom
        priceTextView.text = selectedRoomHotelPrice

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

        dateCheckInEditText = findViewById(R.id.dateCheckIn)
        dateCheckOutEditText = findViewById(R.id.dateCheckOut)
        adultNumEditText = findViewById(R.id.adultNum)
        childrenNumEditText = findViewById(R.id.childrenNum)
        commentsEditText = findViewById(R.id.commentsText)
        bookingButton = findViewById(R.id.bookingButton)

        bookingButton.setOnClickListener {
            saveBooking()
            showSuccessDialog()
        }
    }

    fun isFilled(): Boolean{
        if(TextUtils.isEmpty(dateCheckInEditText.toString())) return false
        if(TextUtils.isEmpty(dateCheckOutEditText.toString())) return false
        if(TextUtils.isEmpty(adultNumEditText.toString())) return false
        if(TextUtils.isEmpty(childrenNumEditText.toString())) return false
        if(TextUtils.isEmpty(commentsEditText.toString())) return false
        return true
    }

    fun showSuccessDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Booking Berhasil")
        builder.setMessage("Hotel pilihanmu sudah berhasil dibooking!")

        builder.setPositiveButton("OK") { dialog, which ->
            val intent = Intent(this@BookingActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun showFailureDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Booking Gagal")
        builder.setMessage("Harap isi formulir diatas")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Dismiss the dialog
        })

        val dialog = builder.create()
        dialog.show()
    }

    private fun saveBooking() {
        dbHelper = DatabaseHelper(this)

        val hotelName = hotelNameTextView.text.toString()
        val roomType = roomNameTextView.text.toString()
        val dateCheckIn = dateCheckInEditText.text.toString()
        val dateCheckOut = dateCheckOutEditText.text.toString()
        val adultNum = adultNumEditText.text.toString().toIntOrNull() ?: 0
        val childrenNum = childrenNumEditText.text.toString().toIntOrNull() ?: 0
        val commentsEditText = commentsEditText.text.toString()
        val price = priceTextView.text.toString().toIntOrNull() ?: 0
        val userId = 1L

        if (dateCheckIn.isNotEmpty() && dateCheckOut.isNotEmpty() && adultNum != null && childrenNum != null) {
            val id = dbHelper.addBooking(hotelName, roomType, dateCheckIn, dateCheckOut, adultNum, childrenNum, commentsEditText, price, userId)

            if (id != -1L) {
                Toast.makeText(this, "Booking saved successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to save booking", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

}