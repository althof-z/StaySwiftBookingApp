package com.example.hotelbookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
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

        // Retrieve user data from the intent
        val username = intent.getStringExtra("USERNAME")

        // Display user data in TextViews or other UI components
        val usernameTextView: TextView = findViewById(R.id.namaUser)
        usernameTextView.text ="$username"

        //======================================================================//
        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recent_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi Adapter dan atur ke RecyclerView
//        hotelAdapter = HotelAdapter(getHotelList())
        hotelAdapter = HotelAdapter(getHotelList(), object : HotelAdapter.OnItemClickListener {
            override fun onItemClick(hotel: DataHotel) {
                // Handle item click, for example, start HotelDetailActivity
                val intent = Intent(this@HomeActivity, HotelDetailActivity::class.java)
                intent.putExtra("selectedHotel", hotel)
                startActivity(intent)
            }
        })
        recyclerView.adapter = hotelAdapter

        // Intent Navigation Bottom
        val logoutImageView = findViewById<ImageView>(R.id.logoutImageView)
        val addButton = findViewById<ImageView>(R.id.addImageView)

        // Set OnClickListener for the logout ImageView
        logoutImageView.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        // Set OnClickListener for the add Button
        addButton.setOnClickListener {
            // Handle add button click, for example, start AddActivity
            val intent = Intent(this@HomeActivity, OrderActivity::class.java)
            startActivity(intent)
        }

        recyclerView.adapter = hotelAdapter
    }

    // Implementasikan metode getHotelList() untuk menyediakan daftar hotel
    private fun getHotelList(): List<DataHotel> {
        // Buat daftar objek Hotel dan kembalikan
        val hotels = mutableListOf<DataHotel>()
        // Tambahkan data hotel ke daftar
        hotels.add(DataHotel("Four Season Hotel", "Gatot Subroto, Jakarta", "Rp. 2,933,333", "In addition to the standard of Indonesia Care, all guests get free Wi-Fi in all rooms and free parking if arriving by car. Conveniently situated in the Gatot Subroto part of Jakarta, this property puts you close to attractions and interesting dining options. Don't leave before paying a visit to the famous National Monument (MONAS). Rated with 5 stars, this high-quality property provides guests with access to restaurant, fitness center and spa on-site.", R.drawable.fourseason))
        hotels.add(DataHotel("Asyana Kemayoran", "Kemayoran, Jakarta - City Center", "Rp. 282,750", "The car parking and the Wi-Fi are always free, so you can stay in touch and come and go as you please. Strategically situated in Kemayoran, allowing you access and proximity to local attractions and sights. Don't leave before paying a visit to the famous National Monument (MONAS). Rated with 3 stars, this high-quality property provides guests with access to restaurant and indoor pool on-site.", R.drawable.asyanakemayoran))
        hotels.add(DataHotel("Aryaduta Menteng", "Menteng, Jakarta", "Rp. 732,782", "In addition to the standard of Indonesia Care, all guests get free Wi-Fi in all rooms and free parking if arriving by car. Strategically situated in Menteng, allowing you access and proximity to local attractions and sights. Don't leave before paying a visit to the famous National Monument (MONAS). Rated with 5 stars, this high-quality property provides guests with access to restaurant, hot tub and outdoor pool on-site.", R.drawable.aryadutamenteng))

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