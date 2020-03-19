package com.al376538.soccergame

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
    val url : String = "https://api.football-data.org/v2/"

    fun getLeagues() : Array<League> {
        return dao.getLeagues()
    }

    fun collectLeagues() {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                //textView.text = "Response: %s".format(response.toString())
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }

    fun parseLeagues(response : JSONObject, listener: Response.Listener<*>) {
        try {
            val leaguesArray = response.getJSONArray("leagues")
            val leagues : Array<League>
            val desiredLeagues : Array<Int>(Arrays.asList(2021, 2015, 2020, 2019, 2003, 2017, 2014))


            for (i in 0..leaguesArray.length()) {
                val aaaaa : JSONObject = leaguesArray.getJSONObject(i)
                val id = aaaaa.getInt("id")
                if(desiredLeagues.contains(id)) {
                    val name = leaguesArray[i] as Int
                    val countryName = leaguesArray[i] as String?
                    val startDate = leaguesArray[i] as Int
                    val endDate = leaguesArray[i] as Int
                }

            }
        }
    }

    /*Esta es la final que añade a la base de datos
    fun receiveSendLeagues(response: JSONObject, listener: Response.Listener<*>) {
        object : AsyncTask<Void?, Void?, Void?>() {
            protected fun doInBackground(vararg voids: Void): Void? {
                //dao.insertPerson(Person(name, address, birthYear))
                dao.insertLeague()
                return null
            }
        }.execute()


    }*/


}