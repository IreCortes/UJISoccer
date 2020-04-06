package com.al376538.soccergame.model.managers

import android.os.AsyncTask
import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.Team
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject

class TeamManager(private var model : Model) {



    fun getTeams(league : Int, listener: Response.Listener<ArrayList<Team>>) {
        Log.d("Mst", "getTeams")
        object : AsyncTask<Void?, Void?, ArrayList<Team>>() {
            override fun doInBackground(vararg params: Void?): ArrayList<Team> {
                return ArrayList(model.dao.getTeams(league).requireNoNulls())

            }

            override fun onPostExecute(team: ArrayList<Team>) {
                listener.onResponse(team)
            }
        }.execute()
    }

    fun collectTeams(league: Int, listener: Response.Listener<ArrayList<Team>>) {
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET,
            "https://api.football-data.org/v2/competitions/$league/teams",
            null,
            Response.Listener { response ->
                parseTeams(league, response, listener)
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
        // Access the RequestQueue through your singleton class.
        model.queue.add(jsonObjectRequest)
    }

    private fun parseTeams(leagueID: Int, response: JSONObject, listener: Response.Listener<ArrayList<Team>>) {
        try {
            val teamsJSONArray = response.getJSONArray("teams")
            val teamsArray: MutableList<Team> = ArrayList(20)

            for(i in 0 until teamsJSONArray.length()) {
                val teamJSONObject : JSONObject = teamsJSONArray.getJSONObject(i)

                val idTeam : Int = teamJSONObject.getInt("id")
                val name : String = teamJSONObject.getString("name")
                val shortName : String = teamJSONObject.getString("shortName")
                val website : String = teamJSONObject.getString("website")
                var founded : Int
                founded = 0
                try{
                    founded = teamJSONObject.getInt("founded")
                }catch (e : JSONException) {
                }

                val clubColors : String = teamJSONObject.getString("clubColors")
                val stadium : String = teamJSONObject.getString("venue")

                val teamObject = Team(idTeam, name, shortName, founded, stadium, clubColors, website, leagueID)
                teamsArray.add(teamObject)
            }
            model.teamArray.addAll(teamsArray)
            receiveSendTeams(listener, model.teamArray)

        } catch (e: JSONException) {
            Log.d("Error", "JSON TEAM EXCEPTION")
        }
    }

    private fun receiveSendTeams(listener: Response.Listener<ArrayList<Team>>, teamArrayList: ArrayList<Team>) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                model.dao.insertTeam(teamArrayList)
                return null
            }

            override fun onPostExecute(result: Void?) {
                //creo que tengo que devolver el array.
                //super.onPostExecute(result)
                listener.onResponse(teamArrayList)
            }
        }.execute()
    }

    fun setTeams(teamArrayList: ArrayList<Team>) {
        model.teamArray.addAll(teamArrayList)
    }

    fun findTeam(name : String): Team {
        for(i in 0 until model.teamArray.size) {
            if(name == model.teamArray[i].name)
                return model.teamArray[i]
        }
        return model.teamArray[0]
    }

    fun getShortName(team: Team): String? {
        return team.shortName
    }

    fun getFoundedYear(team : Team ): String {
        return team.yearFoundation.toString()
    }

    fun getStadium(team : Team): String? {
        return team.stadium
    }

    fun getClubColors(team : Team): String? {
        return team.colour
    }

    fun getId(team : Team): Int{
        return team.idTeam
    }

    fun getURL(team: Team): String? {
        return team.website
    }
}