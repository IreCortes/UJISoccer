package com.al376538.soccergame.model.managers

import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.squad.SquadPlayer
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.jar.Attributes

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


        try{

            val arraySquad = response.getJSONArray("squad")

            for(i in 0 until arraySquad.length()) {
                val squadTeamsJSONObject = arraySquad.getJSONObject(i)
                val name = squadTeamsJSONObject.getString("name")
                val position = squadTeamsJSONObject.getString("position")
                val dateOfBirth = squadTeamsJSONObject.getString("dateOfBirth")
                val countryOfBirth = squadTeamsJSONObject.getString("countryOfBirth")
                val nationality = squadTeamsJSONObject.getString("nationality")
                var shirtnumber : String = "-"
                try{
                    shirtnumber = squadTeamsJSONObject.getInt("shirtNumber").toString()
                }catch(e : JSONException) { }

                val player = SquadPlayer(name, position, dateOfBirth, countryOfBirth, nationality, shirtnumber)

                if(squadTeamsJSONObject.getString("role") == "COACH"){
                    coach = player
                }
                else
                    arrayPlayer.add(player)
            }
            listener.onResponse(arrayPlayer)
        }catch (e : JSONException) {
            Log.d("MSK", "Error JSON")
        }
    }

    fun getCoach(): String {
        return coach.name
    }

    fun getNames(): Array<String> {
        val  arrayNames = ArrayList<String>(arrayPlayer.size)
        for(i in 0 until arrayPlayer.size) {
            arrayNames.add(arrayPlayer[i].name)
        }
        return arrayNames.toTypedArray()
    }
}