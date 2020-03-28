package com.al376538.soccergame.team

import android.os.AsyncTask
import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.Team
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class TeamManager(private var  model : Model) {



    private var teamList = ArrayList<Team>()

    //get the teams for the dao
    fun getTeams(leagueId: Int, listener: Response.Listener<ArrayList<Team>>) {

        object : AsyncTask<Void?, Void?, ArrayList<Team>>() {
            override fun doInBackground(vararg params: Void?): ArrayList<Team> {
                return ArrayList(model.dao.getTeams(leagueId).requireNoNulls())

            }

            override fun onPostExecute(team: ArrayList<Team>) {
                listener.onResponse(team)
            }
        }.execute()
    }

    //get the teams from webpage
    fun collectTeams(idLeague: Int, listener: Response.Listener<ArrayList<Team>>) {
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET,
            "https://api.football-data.org/v2/competitions/$idLeague/standings",
            null,
            Response.Listener { response ->
                parseTeams(
                    response,
                    listener
                )
            },
            Response.ErrorListener {}
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers: MutableMap<String, String> =
                    HashMap()
                headers["X-Auth-Token"] = "e781ecd280c04b36a5cd2bd5d5d142b9"
                return headers
            }
        }
    }

    //Transform JSONObject in teams, and add it to teamleague
    private fun parseTeams(response: JSONObject, listener: Response.Listener<ArrayList<Team>>) {
        Log.d("Error", "Hola")
        val tableJSONArray = response.getJSONArray("standings")
        val teamsJSONObject = tableJSONArray.getJSONObject(0)
        val teamsJSONArray = teamsJSONObject.getJSONArray("Table")
        val leaguesArray: MutableList<Team> = ArrayList(20)

        for (i in 0 until teamsJSONArray.length()) {
            val miObjectJSON: JSONObject = teamsJSONArray.getJSONObject(i)
            Log.d("Error", miObjectJSON.getInt("won").toString())
        }
    }
}