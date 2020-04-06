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
        return  model.standingsManager.getTeamName(pos.toInt() - 1)
    }

    fun getShort(pos : String) : String? {
        return model.teamsManager.getShortName(getTeam(pos))
    }

    fun getYearFounded(pos : String) : String? {
        return model.teamsManager.getFoundedYear(getTeam(pos))
    }

    fun getStadium(pos : String) : String? {
        return model.teamsManager.getStadium(getTeam(pos))
    }

    fun getColorsClub(pos : String) : String? {
        return model.teamsManager.getClubColors(getTeam(pos))
    }

    fun getIdTeam(pos : String) : Int {
        return model.teamsManager.getId(getTeam(pos))
    }

    private fun getTeam(pos : String) : Team{
        return model.teamsManager.findTeam(getTeamName(pos))
    }

    private fun getStandings() {
        if(model.getTeamStandingArrayEmpty()) {
            Model.standingsManager.collectStandings(leagueId.toInt(),
                Listener {
                    view.setAdapter(it, model.standingsManager.getPosTeams())
                }
            )
        }
        else {
            view.setAdapter(model.standingsManager.getstandings(), model.standingsManager.getPosTeams())
        }
    }

    private fun getTeams(leagueId : Int){
        model.teamsManager.getTeams(leagueId, Listener
        { response ->
            onTeamsAvailable(leagueId, response!!)
        })
    }

    private fun onTeamsAvailable(leagueId:Int, teamList: ArrayList<Team>) {
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