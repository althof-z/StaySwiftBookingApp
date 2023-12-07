package com.example.hotelbookings.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataHotel (
    val name: String,
    val location: String,
    val price: String,
    val description: String, // Tambahkan properti description
    val photo: Int = 0
) : Parcelable