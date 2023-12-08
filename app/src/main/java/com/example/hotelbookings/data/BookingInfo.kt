package com.example.hotelbookings.data

data class BookingInfo(
    val bookingId: Long,
    val hotelName: String,
    val roomType: String,
    val dateCheckIn: String,
    val dateCheckOut: String,
    val adult: Int,
    val children: Int,
    val comments: String,
    val price: Int
)
