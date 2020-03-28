package com.al376538.soccergame.team

import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.Team
import com.android.volley.Response.Listener

class TeamPresenter(private var view: TeamActivity, private var model: Model, private var leagueId : String) {

    init {
        getTeams()
    }

    private fun getTeams() {
        model.teamManager.getTeams(leagueId.toInt(), Listener
        { response ->
            onTeamAvailable(response!!)
        })
    }

    private fun onTeamAvailable(teamList: ArrayList<Team>) {
        if (teamList.isEmpty()) {
            Model.teamManager.collectTeams(leagueId.toInt(),
                Listener {
                }
            )
        } else {

        }
    }

}