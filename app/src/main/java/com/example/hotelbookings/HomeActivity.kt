package com.example.hotelbookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookings.adapter.HotelAdapter
import com.example.hotelbookings.data.DataHotel

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var hotelAdapter: HotelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //======================================================================//
        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recent_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi Adapter dan atur ke RecyclerView
        hotelAdapter = HotelAdapter(getHotelList())
        recyclerView.adapter = hotelAdapter

        // Find the ImageView for the logout button
        val logoutImageView = findViewById<ImageView>(R.id.logoutImageView)

        // Set OnClickListener for the logout ImageView
        logoutImageView.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    // Implementasikan metode getHotelList() untuk menyediakan daftar hotel
    private fun getHotelList(): List<DataHotel> {
        // Buat daftar objek Hotel dan kembalikan
        val hotels = mutableListOf<DataHotel>()
        // Tambahkan data hotel ke daftar
        hotels.add(DataHotel("Hotel A", "Location A", "$120", R.drawable.jakarta))
        hotels.add(DataHotel("Hotel B", "Location B", "$150", R.drawable.jakarta2))
        hotels.add(DataHotel("Hotel C", "Location C", "$100", R.drawable.jakarta3))
        hotels.add(DataHotel("Hotel A", "Location A", "$120", R.drawable.jakarta))
        hotels.add(DataHotel("Hotel B", "Location B", "$150", R.drawable.jakarta2))
        hotels.add(DataHotel("Hotel C", "Location C", "$100", R.drawable.jakarta3))
        hotels.add(DataHotel("Hotel A", "Location A", "$120", R.drawable.jakarta))
        hotels.add(DataHotel("Hotel B", "Location B", "$150", R.drawable.jakarta2))
        hotels.add(DataHotel("Hotel C", "Location C", "$100", R.drawable.jakarta3))

        return hotels
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