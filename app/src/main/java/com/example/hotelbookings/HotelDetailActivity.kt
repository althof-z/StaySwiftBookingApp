package com.example.hotelbookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        //Method for Click
        roomAdapter.setOnBookButtonClickListener(object : HotelRoomAdapter.OnBookButtonClickListener {
            override fun onBookButtonClick(position: Int) {
                // Get the selected room
                val selectedRoom = roomAdapter.getRoomAt(position)

                // Handle the click event here, for example, navigate to a new activity
                val intent = Intent(this@HotelDetailActivity, BookingActivity::class.java)
                // Add any necessary data to the intent
                // Pass data to BookingActivity using putExtra
                intent.putExtra("selectedRoomName", selectedRoom.name)
                intent.putExtra("selectedRoomImage", selectedRoom.photo)
                intent.putExtra("selectedHotelName", selectedRoom.nameHotel)
                intent.putExtra("selectedHotelLocation", selectedRoom.location)

                // Pass hotel name and location from the selectedHotel
//                val selectedHotel = intent.getParcelableExtra<DataHotel>("selectedHotel")
//                intent.putExtra("selectedHotelName", selectedHotel?.name)
//                intent.putExtra("selectedHotelLocation", selectedHotel?.location)

                startActivity(intent)
            }
        })


    }

    // Implementasikan metode getHotelList() untuk menyediakan daftar hotel
    private fun getRoomList(): List<DataHotelRoom> {
        // Buat daftar objek Hotel dan kembalikan
        val rooms = mutableListOf<DataHotelRoom>()
        val selectedHotel = intent.getParcelableExtra<DataHotel>("selectedHotel")
        // Tambahkan data hotel ke daftar
        if(selectedHotel?.name == "Four Season Hotel"){
            rooms.add(DataHotelRoom("Deluxe Room","Rp.2,999,933","Four Season Hotel", "Gatot Subroto, Jakarta","2 Bedroom, 2 Bathroom, 1 Kitchen",R.drawable.fourseason))
            rooms.add(DataHotelRoom("Regular Room","Rp.2,999,933", "Four Season Hotel", "Gatot Subroto, Jakarta","2 Bedroom, 2 Bathroom, 1 Kitchen",R.drawable.fourseason))
            rooms.add(DataHotelRoom("Small Room","Rp.2,999,933", "Four Season Hotel", "Gatot Subroto, Jakarta","2 Bedroom, 2 Bathroom, 1 Kitchen",R.drawable.fourseason))
        }else if(selectedHotel?.name == "Asyana Kemayoran"){
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 287,000", "Asyana Kemayoran", "Kemayoran, Jakarta - City Center","1 Bedroom, 1 Bathroom",R.drawable.asyanakemayoran))
            rooms.add(DataHotelRoom("Regular Room","Rp. 287,000", "Asyana Kemayoran", "Kemayoran, Jakarta - City Center","1 Bedroom, 1 Bathroom",R.drawable.asyanakemayoran))
            rooms.add(DataHotelRoom("Small Room","Rp. 287,000", "Asyana Kemayoran", "Kemayoran, Jakarta - City Center","1 Bedroom, 1 Bathroom",R.drawable.asyanakemayoran))
        }else if(selectedHotel?.name == "Aryaduta Menteng"){
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 732,000", "Four Season Hotel", "Gatot Subroto, Jakarta","2 Bedroom, 1 Bathroom",R.drawable.aryadutamenteng))
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 732,000", "Four Season Hotel", "Gatot Subroto, Jakarta","2 Bedroom, 1 Bathroom",R.drawable.aryadutamenteng))
            rooms.add(DataHotelRoom("Deluxe Room","Rp. 732,000","Four Season Hotel", "Gatot Subroto, Jakarta","2 Bedroom, 1 Bathroom",R.drawable.aryadutamenteng))
        }
        return rooms
    }
}