package com.al376538.soccergame.squad

import com.al376538.soccergame.model.Model
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class SquadManager(private var model: Model) {

    private var arrayPlayer = ArrayList<SquadPlayer>()
    private lateinit var coach : SquadPlayer

    fun collectSquadPlayer(idteam : Int, listener: Response.Listener<ArrayList<SquadPlayer>>) {
        val jsonObjectRequest = object : JsonObjectRequest(Method.GET, "https://api.football-data.org/v2/teams/$idteam", null, Response.Listener { response ->
            parseSquad( response, listener)
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

    fun parseSquad(response: JSONObject, listener: Response.Listener<ArrayList<SquadPlayer>>) {

        val arraySquad = response.getJSONArray("squad")

        for(i in 0 until arraySquad.length()) {
            val squadTeamsJSONObject = arraySquad.getJSONObject(i)
            val name = squadTeamsJSONObject.getString("name")
            val position = squadTeamsJSONObject.getString("position")
            val dateOfBirth = squadTeamsJSONObject.getString("dateOfBirth")
            val countryOfBirth = squadTeamsJSONObject.getString("countryOfBirth")
            val nationality = squadTeamsJSONObject.getString("nationality")
            val shirtNumber: Int
            if(squadTeamsJSONObject.getInt("shirtNumber") == null)
                shirtNumber = 0
             else
                shirtNumber = squadTeamsJSONObject.getInt("shirtNumber")

            val player = SquadPlayer(name, position, dateOfBirth, countryOfBirth, nationality, shirtNumber)

            if(squadTeamsJSONObject.getString("role") == "COACH"){
                coach = player
            }
            else
                arrayPlayer.add(player)
        }

        listener.onResponse(arrayPlayer)
    }

    fun getCoach(): SquadPlayer {
        return coach
    }
}