package com.al376538.soccergame

import android.util.Log
import com.al376538.soccergame.database.League
import com.android.volley.Response.Listener


class Presenter(private var view: MainActivity, private var model: Model){

    lateinit var leagueList : ArrayList<League>
    init {
            getLeagues()
    }

    private fun getLeagues() {
        model.getLeagues(Listener
            { response -> onLeagueAvailable(response!!)
            })

    }

   private fun onLeagueAvailable(leaguesList: ArrayList<League>) {
       if (leaguesList.isEmpty()) {
           model.collectLeagues(
               Listener {
                   view.completeSpinner(it)
                   leagueList = leaguesList

               }
           )
       }
       else {
           view.completeSpinner(leaguesList);
       }
   }

    /*fun getCountry( name: String ) : String{
        Log.d("Error", "AQUI TMBN LLEGADO")
        return model.getLeagueCountry(name)
    }*/
}
