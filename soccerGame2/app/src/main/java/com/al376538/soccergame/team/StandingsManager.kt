package com.al376538.soccergame.team

import android.os.AsyncTask
import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.Team
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import java.io.IOException

class StandingsManager(private var  model : Model) {

    private var teamList = ArrayList<TeamInStanding>()

    //get the teams for the dao
    fun getStandings() : ArrayList<TeamInStanding>{
        return teamList
    }

    //get the teams from webpage
    fun collectStandings(idLeague: Int, listener: Response.Listener<ArrayList<TeamInStanding>>) {

        val jsonObjectRequest = object : JsonObjectRequest(Method.GET, "https://api.football-data.org/v2/competitions/$idLeague/standings", null, Response.Listener { response ->
                parseStandings( response, listener)
            }, Response.ErrorListener {}) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers: MutableMap<String, String> =
                    HashMap()
                headers["X-Auth-Token"] = "e781ecd280c04b36a5cd2bd5d5d142b9"
                return headers
            }
        }
        model.queue.add(jsonObjectRequest)
    }

    //Transform JSONObject in teams, and add it to teamleague
    private fun parseStandings(response: JSONObject, listener: Response.Listener<ArrayList<TeamInStanding>>) {
        try {
            val tableJSONArray = response.getJSONArray("standings")
            val teamsJSONObject = tableJSONArray.getJSONObject(1)
            val teamsJSONArray = teamsJSONObject.getJSONArray("table")

            val teamsStandingArray: MutableList<TeamInStanding> = ArrayList(20)

            for (i in 0 until teamsJSONArray.length()) {

                val miObjectJSON: JSONObject = teamsJSONArray.getJSONObject(i)

                val team = miObjectJSON.getJSONObject("team")
                val position = miObjectJSON.getInt("position")
                val playedgames = miObjectJSON.getInt("playedGames")
                val won = miObjectJSON.getInt("won")
                val draw = miObjectJSON.getInt("draw")
                val lost = miObjectJSON.getInt("lost")
                val points = miObjectJSON.getInt("points")
                val goalsFor = miObjectJSON.getInt("goalsFor")
                val goalsAgainst = miObjectJSON.getInt("goalsAgainst")

                var teamStanding = TeamInStanding(team, position, playedgames, won, draw, lost, points, goalsFor, goalsAgainst)
                teamsStandingArray.add(teamStanding)
            }
            teamList.addAll(teamsStandingArray)
            listener.onResponse(teamsStandingArray as ArrayList<TeamInStanding>)

        }catch (e: IOException) {
        }

    }
}