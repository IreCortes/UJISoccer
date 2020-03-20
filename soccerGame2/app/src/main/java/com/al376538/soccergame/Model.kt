package com.al376538.soccergame

import android.os.AsyncTask
import com.al376538.soccergame.database.DAO
import com.al376538.soccergame.database.League
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject


class Model() {

    val dao : DAO = TODO()

    val queue : RequestQueue = TODO()

    fun getLeagues(): Array<League> {
        return dao.getLeagues()
    }

    fun collectLeagues(listener: Response.Listener<*>, error: Response.ErrorListener) {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, "https://api.football-data.org/v2/competitions?plan=TIER_ONE", null,
            Response.Listener { response ->
                //textView.text = "Response: %s".format(response.toString())
                parseLeagues(response, listener)
    },
            Response.ErrorListener { error ->
                // TODO: Handle error

            }
        )
        // Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)
        }

    fun parseLeagues(response : JSONObject, listener: Response.Listener<*>) {
        try {
            val leaguesArray = response.getJSONArray("leagues")
            val leagues : Array<League>
            val desiredLeagues = arrayOf(2021, 2015, 2020, 2019, 2003, 2017, 2014)

            for (i in 0..leaguesArray.length()) {
                val miObjetoJSON : JSONObject = leaguesArray.getJSONObject(i)
                val id = miObjetoJSON.getInt("id")
                if(desiredLeagues.contains(id)) {
                    val name = miObjetoJSON.getString("name")
                    val area = miObjetoJSON.getJSONObject("area")
                    val countryName = area.getString("name")
                    val currentSeason = miObjetoJSON.getJSONObject("currentSeason")
                    val startDate = currentSeason.getString("startDate")
                    val endDate = currentSeason.getString("endDate")

                    val league = League(id, name, countryName, startDate, endDate)
                }
                //falta meterlas en el array
                //llamar a la función de abajo

            }
        }
    }

    //Esta es la final que añade a la base de datos
    fun receiveSendLeagues(response: JSONObject, listener: Response.Listener<*>, league: League) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                TODO("Not yet implemented")
                dao.insertLeague(league)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

            }
        }.execute()
        //post execute
    }


}