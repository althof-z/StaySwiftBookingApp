package com.example.hotelbookings.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookings.HotelDetailActivity
import com.example.hotelbookings.R
import com.example.hotelbookings.data.DataHotel

class HotelAdapter (private val hotels: List<DataHotel>, private val listener: OnItemClickListener):
    RecyclerView.Adapter<HotelAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Inisialisasi tampilan dari layout hotel_card
        val hotelNameTextView: TextView = itemView.findViewById(R.id.tv_hotelName)
        val hotelLocationTextView: TextView = itemView.findViewById(R.id.tv_hotelLoc)
        val hotelPriceTextView: TextView = itemView.findViewById(R.id.tv_hotelPrice)
        val hotelPicture: ImageView = itemView.findViewById(R.id.iv_image)
        val bookButton: Button = itemView.findViewById(R.id.btn_book)
    }

    // Override onCreateViewHolder, onBindViewHolder, getItemCount
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate layout hotel_card
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hotel_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data ke tampilan dalam layout hotel_card
        val hotel = hotels[position]
        holder.hotelNameTextView.text = hotel.name
        holder.hotelLocationTextView.text = hotel.location
        holder.hotelPriceTextView.text = hotel.price

        // Load image into ImageView
        holder.hotelPicture.setImageResource(hotel.photo)

        // Implementasi tindakan ketika tombol "Book" ditekan
        holder.bookButton.setOnClickListener {
            // Lakukan sesuatu saat tombol "Book" ditekan

            // Contoh: Start HotelReservationActivity
            val intent = Intent(holder.itemView.context, HotelDetailActivity::class.java)
            // Anda dapat menyertakan data tambahan jika diperlukan, misalnya hotel yang dipesan
            intent.putExtra("selectedHotel", hotel)
            holder.itemView.context.startActivity(intent)
        }

        //Kocak
        // Implementasi tindakan ketika item di klik
        holder.itemView.setOnClickListener {
            listener.onItemClick(hotel)
        }

    }

    override fun getItemCount(): Int {
        // Kembalikan jumlah item dalam daftar hotel
        return hotels.size
    }

    // Interface untuk handle item click
    interface OnItemClickListener {
        fun onItemClick(hotel: DataHotel)
    }

}