package com.al376538.soccergame.model.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "League",
        indices = [(Index(value = ["idLeague"]))]
)
data class League(
    @PrimaryKey()
    var idLeague: Int,
    var name: String?,
    var countryName: String?,
    var startDate: String?,
    var endDate: String?
) : Comparable<League>, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun compareTo(other: League): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idLeague)
        parcel.writeString(name)
        parcel.writeString(countryName)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
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

}