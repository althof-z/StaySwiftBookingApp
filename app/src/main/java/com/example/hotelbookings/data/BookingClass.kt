package com.example.hotelbookings.data

data class BookingClass (
    val bookingId: Long,
    val dateCheckIn: String,
    val dateCheckOut: String,
    val adult: Int,
    val children: Int,
    val comments: String
)