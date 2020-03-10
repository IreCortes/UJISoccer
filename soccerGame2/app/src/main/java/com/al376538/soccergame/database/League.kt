package com.al376538.soccergame.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "League")
data class League(
    @PrimaryKey()
    var id: Int,
    var name: String?,
    var countryName: String?,
    var startDate: Int,
    var endDate: Int
) : Parcelable, Comparable<League> {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(countryName)
        parcel.writeInt(startDate)
        parcel.writeInt(endDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<League> {
        override fun createFromParcel(parcel: Parcel): League {
            return League(parcel)
        }

        override fun newArray(size: Int): Array<League?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun compareTo(other: League): Int {
        TODO("Not yet implemented")
    }
}