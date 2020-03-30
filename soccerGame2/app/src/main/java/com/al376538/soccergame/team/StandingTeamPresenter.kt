package com.al376538.soccergame.team

import com.al376538.soccergame.model.Model
import com.al376538.soccergame.standings.TeamInStanding
import com.android.volley.Response.Listener

class StandingTeamPresenter(private var view: StandingTeamActivity, private var model: Model, private var leagueId : String) {

    init {
        getTeams()
    }

    private fun getTeams() {
        onTeamAvailable(model.standingsManager.getStandings())

    }

    private fun onTeamAvailable(teamList: ArrayList<TeamInStanding>) {

            Model.standingsManager.collectStandings(leagueId.toInt(),
                Listener {
                    view.setAdapter(it, model.standingsManager.getPosTeams())
                }
            )
    }
}