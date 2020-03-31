package com.al376538.soccergame.team

import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.Team
import com.al376538.soccergame.standings.TeamInStanding
import com.android.volley.Response.Listener

class StandingTeamPresenter(private var view: StandingTeamActivity, private var model: Model, private var leagueId : String) {

    init {
        getStandings()
        getTeams(leagueId.toInt())
    }

    fun getTeamName(pos : String) : String {
        Log.d("Error", model.teamsManager.teamArray.isEmpty().toString())
        return  model.standingsManager.getTeamName(pos.toInt() - 1)
    }

    private fun getStandings() {
        onStandingsAvailable(model.standingsManager.getStandings())

    }

    private fun getTeams(leagueId : Int){
        Log.d("Mst", "getTeams")
        model.teamsManager.getTeams(leagueId, Listener
        { response ->
            onTeamsAvailable(leagueId, response!!)
        })
    }

    private fun onStandingsAvailable(teamList: ArrayList<TeamInStanding>) {
        Model.standingsManager.collectStandings(leagueId.toInt(),
            Listener {
                view.setAdapter(it, model.standingsManager.getPosTeams())
            }
        )
    }

    private fun onTeamsAvailable(leagueId:Int, teamList: ArrayList<Team>) {
        Log.d("Mst", "onTeamsAvailable")
        Log.d("Mst", "is empty: ${teamList.isEmpty()}")
        if(teamList.isEmpty()) {
            model.teamsManager.collectTeams(leagueId, Listener {
                model.teamsManager.setTeams(teamList)
            })
        }
        else {
            model.teamsManager.setTeams(teamList)
        }
    }
}