package com.example.hotelbookings.data

import android.os.Parcel
import android.os.Parcelable

data class DataHotelRoom(
    val name: String, //Nama Ruangan
    val price: String,
    val nameHotel: String, //Nama Hotel
    val location: String, //Lokasi
    val description: String, // Add the description property
    val photo: Int = 0
) : Parcelable {
    // Implementing Parcelable interface requires a CREATOR field
    companion object CREATOR : Parcelable.Creator<DataHotelRoom> {
        override fun createFromParcel(parcel: Parcel): DataHotelRoom {
            return DataHotelRoom(parcel)
        }

        override fun newArray(size: Int): Array<DataHotelRoom?> {
            return arrayOfNulls(size)
        }
    }

    // Secondary constructor for Parcelable
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    // Override the describeContents() method
    override fun describeContents(): Int {
        return 0
    }

    // Override the writeToParcel() method
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(nameHotel)
        parcel.writeString(location)
        parcel.writeString(description)
        parcel.writeInt(photo)
    }
}
