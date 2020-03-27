package com.al376538.soccergame

import android.util.Log
import com.al376538.soccergame.database.League
import com.android.volley.Response.Listener


class Presenter(private var view: MainActivity, private var model: Model){

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
                   Log.d("Name1", it.isEmpty().toString())
                   view.completeSpinner(it)
                   model.setLeagueList(it)
               }
           )
       }
       else {
           view.completeSpinner(leaguesList);
           model.setLeagueList(leaguesList)
       }
   }

    fun getCountry(name : String) : String{
        return model.getLeagueCountry(name)
    }

    fun getEndDate(name : String) : String{
        return model.getLeagueEnd(name)
    }

    fun getInitDate(name : String) : String{
        return model.getLeagueInit(name)
    }
}
