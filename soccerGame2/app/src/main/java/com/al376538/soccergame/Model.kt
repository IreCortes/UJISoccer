package com.al376538.soccergame


import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Room.databaseBuilder
import com.al376538.soccergame.database.DAO
import com.al376538.soccergame.database.DataBase
import com.al376538.soccergame.database.League
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.IOException

object Model {
    private lateinit var dao: DAO
    private lateinit var queue: RequestQueue
    private lateinit var leagueList : ArrayList<League>
    //private lateinit var actualLeague: League

    private fun model(context: Context) {
        val database : DataBase = databaseBuilder(
            context,
            DataBase ::class.java,
            "Database"
        ).build()

        dao = database.Dao()
        queue = Volley.newRequestQueue(context)
    }

    fun  getInstanceModel(context: Context): Model {
            model(context)
        return this
    }

    fun getLeagues(listener: Response.Listener<ArrayList<League>>) {

        object : AsyncTask<Void?, Void?, ArrayList<League>>() {
            override fun doInBackground(vararg params: Void?): ArrayList<League> {
                return ArrayList(dao.getLeagues().requireNoNulls())

            }
            override fun onPostExecute(league: ArrayList<League>) {
                listener.onResponse(league)
            }
        }.execute()
    }

    fun collectLeagues(listener: Response.Listener<ArrayList<League>>) {

        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET,
            "https://api.football-data.org/v2/competitions?plan=TIER_ONE",
            null,
            Response.Listener { response -> parseLeagues(response, listener)
            },
            Response.ErrorListener {}
        )
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers: MutableMap<String, String> =
                    HashMap()
                headers["X-Auth-Token"] = "e781ecd280c04b36a5cd2bd5d5d142b9"
                return headers
            }
        }
        // Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)
    }

    private fun parseLeagues(response: JSONObject, listener: Response.Listener<ArrayList<League>>) {
        try {
            val leaguesJSONArray = response.getJSONArray("competitions")
            val leaguesArray : MutableList<League> = ArrayList(7)
            val desiredLeagues = arrayOf(2021, 2015, 2002, 2019, 2003, 2017, 2014)

            for (i in 0 until leaguesJSONArray.length()) {

                val miObjectJSON : JSONObject = leaguesJSONArray.getJSONObject(i)
                val id = miObjectJSON.getInt("id")


                if(desiredLeagues.contains(id)) {

                    val name = miObjectJSON.getString("name")
                    val area = miObjectJSON.getJSONObject("area")
                    val countryName = area.getString("name")
                    val currentSeason = miObjectJSON.getJSONObject("currentSeason")
                    val startDate = currentSeason.getString("startDate")
                    val endDate = currentSeason.getString("endDate")

                    val league = League(id, name, countryName, startDate, endDate)
                    leaguesArray.add(league)

                }
                leagueList = ArrayList(leaguesArray)
                //actualLeague = leagueList[0]
                receiveSendLeagues(response, listener, ArrayList(leaguesArray))
            }
        }
        catch (e: IOException) {
        }
    }

    //Esta es la final que añade a la base de datos
    private fun receiveSendLeagues(response: JSONObject, listener: Response.Listener<ArrayList<League>>, leagueArrayList: ArrayList<League>) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                    dao.insertLeague(leagueArrayList)
                return null
            }

            override fun onPostExecute(result: Void?) {
                //creo que tengo que devolver el array.
                //super.onPostExecute(result)
                listener.onResponse(leagueArrayList)
            }
        }.execute()
    }

    /*fun getLeagueCountry(nameSelected : String) : String {
        Log.d("Error", "AQUI TMBN TMBN LLEGADO")
        Log.d("Error", nameSelected)
        Log.d("Error", actualLeague.countryName.toString())
        if (!nameSelected.equals(actualLeague.countryName)){
            for(league in leagueList) {
                if(league.countryName.toString() == nameSelected) {
                    actualLeague = league
                    return league.countryName.toString()
                }

            }
        }
        return actualLeague.countryName.toString()
    }*/
}