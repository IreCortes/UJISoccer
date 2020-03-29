package com.al376538.soccergame.team

import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.Team
import com.android.volley.Response.Listener

class StandingsPresenter(private var view: StandingsActivity, private var model: Model, private var leagueId : String) {

    init {
        getTeams()
    }

    private fun getTeams() {
        onTeamAvailable(model.teamManager.getStandings())

    }

    private fun onTeamAvailable(teamList: ArrayList<TeamInStanding>) {
        if (teamList.isEmpty()) {
            Model.teamManager.collectStandings(leagueId.toInt(),
                Listener {
                    view.setAdapter(it)
                }
            )
        } else {
            view.setAdapter(teamList)
        }
    }

}