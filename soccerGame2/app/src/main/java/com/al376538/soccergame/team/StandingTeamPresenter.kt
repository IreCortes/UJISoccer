package com.al376538.soccergame.team

import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.Team
import com.al376538.soccergame.standings.TeamInStanding
import com.android.volley.Response.Listener

class StandingTeamPresenter(private var view: StandingTeamActivity, private var model: Model, private var leagueId : String) {

    init {
        getStandings()
        Log.d("Error", leagueId)
        getTeams(leagueId.toInt())
    }

    private fun getStandings() {
        onStandingsAvailable(model.standingsManager.getStandings())

    }

    private fun getTeams(leagueId : Int){
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
        if(teamList.isEmpty()) {
            model.teamsManager.collectTeams(leagueId, Listener {
                model.teamsManager.setTeams(teamList)
            })
        }
    }
}