package com.al376538.soccergame.model


import android.content.Context
import android.util.Log
import androidx.room.Room.databaseBuilder
import com.al376538.soccergame.model.managers.MainManager
import com.al376538.soccergame.model.database.DAO
import com.al376538.soccergame.model.database.DataBase
import com.al376538.soccergame.model.database.League
import com.al376538.soccergame.model.database.Team
import com.al376538.soccergame.model.managers.SquadManager
import com.al376538.soccergame.model.managers.StandingsManager
import com.al376538.soccergame.model.managers.TeamManager
import com.al376538.soccergame.standings.TeamInStanding
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

object Model {
    internal lateinit var dao: DAO
    internal lateinit var queue: RequestQueue

    lateinit var mainManager: MainManager
    lateinit var standingsManager: StandingsManager
    lateinit var teamsManager: TeamManager
    lateinit var squadManager: SquadManager


    internal val leagueList = ArrayList<League>()
     var teamStandingArray = ArrayList<TeamInStanding>()
    internal var teamArray = ArrayList<Team>()

    lateinit var currentLeague : League


    //Model create
    private fun model(context: Context) {
        val database: DataBase = databaseBuilder(
            context,
            DataBase::class.java,
            "Database"
        ).build()

        dao = database.Dao()
        queue = Volley.newRequestQueue(context)
        mainManager = MainManager(this)
        standingsManager =
            StandingsManager(this)
        teamsManager = TeamManager(this)
        squadManager = SquadManager(this)
    }

    //Get an instance of a model
    fun getInstanceModel(context: Context): Model {
        model(context)
        return this
    }

    fun getleagueListEmpty(): Boolean {
        return leagueList.isEmpty()
    }
    fun getTeamStandingArrayEmpty(): Boolean {
        return teamStandingArray.isEmpty()
    }
    fun getTeamArray(): Boolean {
        return teamArray.isEmpty()
    }
    fun reset() {
        teamStandingArray.clear()
        Log.d("MKT", teamStandingArray.isEmpty().toString())
        teamArray.clear()
    } 
}