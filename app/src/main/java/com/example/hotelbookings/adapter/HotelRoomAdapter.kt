package com.example.hotelbookings.adapter

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

    private var onBookButtonClickListener: OnBookButtonClickListener? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Inisialisasi tampilan dari layout room_card
        val roomNameTextView: TextView = itemView.findViewById(R.id.tv_roomName)
        val roomPriceTextView: TextView = itemView.findViewById(R.id.tv_roomPrice)
        val roomDescTextView: TextView = itemView.findViewById(R.id.tv_roomDesc)
        val roomPicture: ImageView = itemView.findViewById(R.id.iv_room)
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

        holder.itemView.findViewById<Button>(R.id.btn_book).setOnClickListener {
            onBookButtonClickListener?.onBookButtonClick(position)
        }

    }

    override fun getItemCount(): Int {
        // Kembalikan jumlah item dalam daftar room
        return rooms.size
    }

    interface OnBookButtonClickListener {
        fun onBookButtonClick(position: Int)
    }

    fun setOnBookButtonClickListener(listener: OnBookButtonClickListener) {
        this.onBookButtonClickListener = listener
    }

    fun getRoomAt(position: Int): DataHotelRoom {
        return rooms[position]
    }

}