package com.al376538.soccergame.team

import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.Team
import com.android.volley.Response.Listener

class StandingsPresenter(private var view: StandingsActivity, private var model: Model, private var leagueId : String) {

    init {
        getTeams()
    }

    private fun getTeams() {
        model.teamManager.getStandings(leagueId.toInt(), Listener
        { response ->
            onTeamAvailable(response!!)
        })
    }

    private fun onTeamAvailable(teamList: ArrayList<Team>) {
        if (teamList.isEmpty()) {
            Model.teamManager.collectStandings(leagueId.toInt(),
                Listener {
                }
            )
        } else {

        }
    }

}