package com.al376538.soccergame.model.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Team",
    foreignKeys = [ForeignKey(
        entity = League::class,
        parentColumns = ["idLeague"],
        childColumns = ["leagueID"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )],
    indices = arrayOf(Index(value = ["leagueID"]))
)
data class Team(
    @PrimaryKey()
    var idTeam: Int,
    var name: String?,
    var shortName: String?,
    var yearFoundation: Int,
    var stadium: String?,
    var colour: String?,
    var website: String?,
    var leagueID: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idTeam)
        parcel.writeString(name)
        parcel.writeString(shortName)
        parcel.writeInt(yearFoundation)
        parcel.writeString(stadium)
        parcel.writeString(colour)
        parcel.writeString(website)
        parcel.writeInt(leagueID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Team> {
        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }

}
