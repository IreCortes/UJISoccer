package com.al376538.soccergame.team

import android.util.Log
import com.al376538.soccergame.main.MainActivity
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.League
import com.al376538.soccergame.model.database.Team
import com.android.volley.Response.Listener

class TeamPresenter(private var view: TeamActivity, private var model: Model) {

    init {
        getTeams()
    }

    private fun getTeams() {
        model.teamManager.getTeams(1, Listener
        { response ->
            onLeagueAvailable(response!!)
        })
    }

    private fun onLeagueAvailable(leaguesList: ArrayList<Team>) {
        if (leaguesList.isEmpty()) {
            Model.mainManager.collectLeagues(
                Listener {
                    Log.d("Name1", it.isEmpty().toString())
                    //view.completeSpinner(it)
                    Model.mainManager.setLeagueList(it)
                }
            )
        } else {
            //view.completeSpinner(leaguesList);
            //Model.mainManager.setLeagueList(leaguesList)
        }
    }

}