package com.al376538.soccergame.model.managers

import android.os.AsyncTask
import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.League
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import java.io.IOException

class MainManager(private var model : Model) {

    //get the leagues from the dao
    fun getLeagues(listener: Response.Listener<ArrayList<League>>) {

        object : AsyncTask<Void?, Void?, ArrayList<League>>() {
            override fun doInBackground(vararg params: Void?): ArrayList<League> {
                return ArrayList(model.dao.getLeagues().requireNoNulls())

            }

            override fun onPostExecute(league: ArrayList<League>) {
                listener.onResponse(league)
            }
        }.execute()
    }

    //get the leagues from webpage
    fun collectLeagues(listener: Response.Listener<ArrayList<League>>) {
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET,
            "https://api.football-data.org/v2/competitions?plan=TIER_ONE",
            null,
            Response.Listener { response ->
                parseLeagues(
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
        // Access the RequestQueue through your singleton class.
        model.queue.add(jsonObjectRequest)
    }

    //Transform JSONObject in leagues, and add it to leagueList
    private fun parseLeagues(response: JSONObject, listener: Response.Listener<ArrayList<League>>) {
        try {
            val leaguesJSONArray = response.getJSONArray("competitions")
            val leaguesArray: MutableList<League> = ArrayList(7)
            val desiredLeagues = arrayOf(2021, 2015, 2002, 2019, 2003, 2017, 2014)

            for (i in 0 until leaguesJSONArray.length()) {

                val miObjectJSON: JSONObject = leaguesJSONArray.getJSONObject(i)
                val id = miObjectJSON.getInt("id")


                if (desiredLeagues.contains(id)) {

                    val name = miObjectJSON.getString("name")
                    val area = miObjectJSON.getJSONObject("area")
                    val countryName = area.getString("name")
                    val currentSeason = miObjectJSON.getJSONObject("currentSeason")
                    val startDate = currentSeason.getString("startDate")
                    val endDate = currentSeason.getString("endDate")

                    val league = League(id, name, countryName, startDate, endDate)
                    leaguesArray.add(league)
                }
            }
            receiveSendLeagues(
                listener,
                ArrayList(leaguesArray)
            )
        } catch (e: IOException) {
        }
    }

    private fun receiveSendLeagues(listener: Response.Listener<ArrayList<League>>, leagueArrayList: ArrayList<League>) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                model.dao.insertLeague(leagueArrayList)

                return null
            }

            override fun onPostExecute(result: Void?) {
                //creo que tengo que devolver el array.
                //super.onPostExecute(result)
                listener.onResponse(leagueArrayList)
            }
        }.execute()
    }

    fun setLeagueList(value: ArrayList<League>) {
        model.leagueList.addAll(value)
        model.currentLeague = model.leagueList.component1()
    }

    fun getLeagueCountry(): String {
        return model.currentLeague.countryName.toString()
    }

    fun getLeagueEnd(): String {
        return model.currentLeague.endDate.toString()
    }

    fun getLeagueInit(): String {
        Log.d("Error" ,model.leagueList.isEmpty().toString())
        return model.currentLeague.startDate.toString()
    }

    fun getLeagueId() : String {
        return model.currentLeague.idLeague.toString()
    }

    fun setCurrentLeague(nameSelected: String){
        for (league in model.leagueList) {
            if (league.name.toString().equals(nameSelected)) {
                model.currentLeague = league
            }
        }
    }

}