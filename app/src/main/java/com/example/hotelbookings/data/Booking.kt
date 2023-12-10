package com.example.hotelbookings.data

data class Booking(
    val id: Long,
    val dateCheckIn: String,
    val dateCheckOut: String,
    val adultNum: Int,
    val childrenNum: Int,
    val commentsText: String,
    val reserverName: String
)
