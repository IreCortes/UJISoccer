package com.al376538.soccergame.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAO {
    @Query("SELECT * FROM League")
    fun getLeagues(): Array<League>

    @Query("SELECT * FROM Team WHERE leagueID like :league")
    fun getTeams(league : Int): Array<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeague(league: League)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)
}