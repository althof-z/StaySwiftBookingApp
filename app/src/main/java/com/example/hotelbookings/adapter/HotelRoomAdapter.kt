package com.example.hotelbookings.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookings.R
import com.example.hotelbookings.data.DataHotelRoom

class HotelRoomAdapter (private val rooms: List<DataHotelRoom>):
    RecyclerView.Adapter<HotelRoomAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Inisialisasi tampilan dari layout room_card
        val roomNameTextView: TextView = itemView.findViewById(R.id.tv_roomName)
        val roomPriceTextView: TextView = itemView.findViewById(R.id.tv_roomPrice)
        val roomDescTextView: TextView = itemView.findViewById(R.id.tv_roomDesc)
        val roomPicture: ImageView = itemView.findViewById(R.id.iv_room)
//        val roombookButton: Button = itemView.findViewById(R.id.btn_book)
    }

    // Override onCreateViewHolder, onBindViewHolder, getItemCount
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate layout room_card
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hotelroom_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data ke tampilan dalam layout room_card
        val room = rooms[position]
        holder.roomNameTextView.text = room.name
        holder.roomPriceTextView.text = room.price
        holder.roomDescTextView.text = room.description
        // Load image into ImageView
        holder.roomPicture.setImageResource(room.photo)

//        // Implementasi tindakan ketika tombol "Book" ditekan
//        holder.bookButton.setOnClickListener {
//            // Lakukan sesuatu saat tombol "Book" ditekan
//
//            // Contoh: Start roomReservationActivity
//            val intent = Intent(holder.itemView.context, HotelDetailActivity::class.java)
//            // Anda dapat menyertakan data tambahan jika diperlukan, misalnya room yang dipesan
//            intent.putExtra("selectedroom", room)
//            holder.itemView.context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        // Kembalikan jumlah item dalam daftar room
        return rooms.size
    }

}