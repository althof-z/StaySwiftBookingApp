package com.example.hotelbookings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookings.R
import com.example.hotelbookings.data.Booking

class BookingAdapter(private var bookings: List<Booking>) : RecyclerView.Adapter<BookingAdapter.ViewHolder>() {

    //DB Delete

    fun updateData(newBookingList: List<Booking>) {
        bookings = newBookingList
        notifyDataSetChanged()
    }

    // Define an interface for item click events
    interface OnItemClickListener {
        fun onItemClick(bookingId: Long)
    }

    // Variable to hold the click listener
    private var itemClickListener: OnItemClickListener? = null

    // Function to set the click listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define views in the item layout
        val dateCheckInTextView: TextView = itemView.findViewById(R.id.textView9)
        val dateCheckOutTextView: TextView = itemView.findViewById(R.id.textView10)
        val adultNumTextView: TextView = itemView.findViewById(R.id.textView17)
        val childrenNumTextView: TextView = itemView.findViewById(R.id.textView21)
        val commentsTextView: TextView = itemView.findViewById(R.id.textComment)
        val reserverNameTextView: TextView = itemView.findViewById(R.id.textView7) // Add this line
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val booking = bookings[position]

        // Bind data to views
        holder.dateCheckInTextView.text = booking.dateCheckIn
        holder.dateCheckOutTextView.text = booking.dateCheckOut
        holder.adultNumTextView.text = booking.adultNum.toString()
        holder.childrenNumTextView.text = booking.childrenNum.toString()
        holder.commentsTextView.text = booking.commentsText
        holder.reserverNameTextView.text = booking.reserverName // Set the new TextView

        // Set click listener for the item view
        holder.itemView.setOnClickListener {
            // Call the onItemClick method of the listener, passing the bookingId
            itemClickListener?.onItemClick(booking.id)
        }
    }

    override fun getItemCount(): Int {
        return bookings.size
    }

}
