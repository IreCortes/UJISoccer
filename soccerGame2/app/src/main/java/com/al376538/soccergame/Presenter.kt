package com.al376538.soccergame

import android.util.Log
import com.al376538.soccergame.database.League
import com.android.volley.Response
import com.android.volley.Response.Listener


class Presenter(private var view: MainActivity, private var model: Model){

    //private lateinit var leagues: Array<League>

    init {
        getLeagues()
    }

    private fun getLeagues() {
        model.getLeagues(Listener
            { response -> onLeagueAvailable(response!!)
            })
    }

   private fun onLeagueAvailable(leaguesList: ArrayList<League>) {
       Log.d("Funciona", "estoy en onLeagueAvailable")
       if(leaguesList.isEmpty()) {
           model.collectLeagues(
               Listener { leagues ->
                   val names: ArrayList<String?> = ArrayList()
                   for ((_, name) in leagues) {
                       names.add(name)
                   }
                   view.completeSpinner(names)
                   //Log.d("qwer", "onResponse: $leagues")
               }
               /*Response.ErrorListener { error ->
                   //processError(error.message)
               }*/
           )
       }
   }
}
