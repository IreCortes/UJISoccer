package com.al376538.soccergame.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlin.collections.ArrayList

@Dao
interface DAO {
    @Query("SELECT * FROM League")
    fun getLeagues(): List<League>

    @Query("SELECT * FROM Team WHERE leagueID like :league")
    fun getTeams(league : Int): List<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeague(league: League)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)
}