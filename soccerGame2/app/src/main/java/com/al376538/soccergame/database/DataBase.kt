package com.al376538.soccergame.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(League::class, Team::class), version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun Dao(): DAO
}
