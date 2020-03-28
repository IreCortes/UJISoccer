package com.al376538.soccergame.model


import android.content.Context
import android.os.AsyncTask
import androidx.room.Room.databaseBuilder
import com.al376538.soccergame.main.MainManager
import com.al376538.soccergame.model.database.DAO
import com.al376538.soccergame.model.database.DataBase
import com.al376538.soccergame.model.database.League
import com.al376538.soccergame.model.database.Team
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

object Model {
    internal lateinit var dao: DAO
    internal lateinit var queue: RequestQueue

    lateinit var mainManager: MainManager


    private var teamList = ArrayList<Team>()

    //Model create
    private fun model(context: Context) {
        val database: DataBase = databaseBuilder(
            context,
            DataBase::class.java,
            "Database"
        ).build()

        dao = database.Dao()
        queue = Volley.newRequestQueue(context)
        mainManager = MainManager(this)
    }

    //Get an instance of a model
    fun getInstanceModel(context: Context): Model {
        model(context)
        return this
    }

    //get the teams for the dao
    fun getTeams(leagueId: Int, listener: Response.Listener<ArrayList<Team>>) {
        object : AsyncTask<Void?, Void?, ArrayList<Team>>() {
            override fun doInBackground(vararg params: Void?): ArrayList<Team> {
                return ArrayList(dao.getTeams(leagueId).requireNoNulls())

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
        val teamJSONArray = response.getJSONArray("standings")
        val leaguesArray: MutableList<Team> = ArrayList(20)

        for (i in 0 until teamJSONArray.length()) {

            val miObjectJSON: JSONObject = teamJSONArray.getJSONObject(i)
        }

    }




}