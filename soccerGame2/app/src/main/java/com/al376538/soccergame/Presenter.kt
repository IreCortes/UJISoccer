package com.al376538.soccergame

import android.util.Log
import com.al376538.soccergame.database.League
import com.android.volley.Response
import com.android.volley.Response.Listener


class Presenter {

    var model: Model = TODO()
    var view: MainActivity
    var leagues: Array<League>

    constructor(view: MainActivity, model: Model) {
        this.view = view
        this.model = model
        getLeagues()
    }

    fun getLeagues() {
        model.getLeagues(
            Listener<ArrayList<League?>?>
            { response -> onLeagueAvailable(response)
            })
    }

   fun onLeagueAvailable(leaguesList: ArrayList<League?>) {
       if(leaguesList.isEmpty()) {
           model.collectLeagues(Listener<ArrayList<League>> { leagues ->
               val names: ArrayList<String?> = ArrayList()
               for ((_, name) in leagues) {
                   names.add(name)
               }
               //view.FillSpinner(names)
               //Log.d("qwer", "onResponse: $leagues")
           }, Response.ErrorListener { error ->
               //processError(error.message)
           })
       }
   }
}
