package com.al376538.soccergame.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DAO {
    @Query("SELECT * FROM League")
    fun getLeagues(): Array<League>

    @Query("SELECT * FROM Team WHERE leagueID like :league")
    fun getTeams(league : Int): Array<Team>
}