package com.example.hotelbookings.data

import android.os.Parcel
import android.os.Parcelable

data class DataHotel (
    val name: String,
    val location: String,
    val price: String,
    val photo: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeString(price)
        parcel.writeInt(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataHotel> {
        override fun createFromParcel(parcel: Parcel): DataHotel {
            return DataHotel(parcel)
        }

        override fun newArray(size: Int): Array<DataHotel?> {
            return arrayOfNulls(size)
        }
    }
}