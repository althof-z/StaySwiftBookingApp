package com.example.hotelbookings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookings.adapter.HotelAdapter
import com.example.hotelbookings.adapter.HotelRoomAdapter
import com.example.hotelbookings.data.DataHotel
import com.example.hotelbookings.data.DataHotelRoom

class HotelDetailActivity : AppCompatActivity() {
    private lateinit var roomRecyclerView: RecyclerView
    private lateinit var roomAdapter: HotelRoomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail)

        //======================================================================//
        // Inisialisasi RecyclerView
        roomRecyclerView = findViewById(R.id.room_recycler)
        roomRecyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi Adapter dan atur ke RecyclerView
        roomAdapter = HotelRoomAdapter(getRoomList())
        roomRecyclerView.adapter = roomAdapter

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
        val hotelDescriptionTextView: TextView = findViewById(R.id.detailHotelDescriptionTextView)

        selectedHotel?.let {
            detailImageView.setImageResource(it.photo)
            hotelNameTextView.text = it.name
            hotelLocationTextView.text = it.location
            hotelDescriptionTextView.text = it.description
        }

    }

    // Implementasikan metode getHotelList() untuk menyediakan daftar hotel
    private fun getRoomList(): List<DataHotelRoom> {
        // Buat daftar objek Hotel dan kembalikan
        val rooms = mutableListOf<DataHotelRoom>()
        val selectedHotel = intent.getParcelableExtra<DataHotel>("selectedHotel")
        // Tambahkan data hotel ke daftar
        if(selectedHotel?.name == "Four Season Hotel"){
            rooms.add(DataHotelRoom("Deluxe Room","Rp.2,999,933","2 Bedroom, 2 Bathroom, 1 Kitchen",R.drawable.fourseason))
            rooms.add(DataHotelRoom("Deluxe Room","Rp.2,999,933","2 Bedroom, 2 Bathroom, 1 Kitchen",R.drawable.fourseason))
            rooms.add(DataHotelRoom("Deluxe Room","Rp.2,999,933","2 Bedroom, 2 Bathroom, 1 Kitchen",R.drawable.fourseason))
        }else if(selectedHotel?.name == "Asyana Kemayoran"){
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 287,000","1 Bedroom, 1 Bathroom",R.drawable.asyanakemayoran))
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 287,000","1 Bedroom, 1 Bathroom",R.drawable.asyanakemayoran))
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 287,000","1 Bedroom, 1 Bathroom",R.drawable.asyanakemayoran))
        }else if(selectedHotel?.name == "Aryaduta Menteng"){
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 732,000","2 Bedroom, 1 Bathroom",R.drawable.aryadutamenteng))
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 732,000","2 Bedroom, 1 Bathroom",R.drawable.aryadutamenteng))
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 732,000","2 Bedroom, 1 Bathroom",R.drawable.aryadutamenteng))
        }
        return rooms
    }
}