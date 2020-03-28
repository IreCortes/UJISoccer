package com.al376538.soccergame.main

import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.League
import com.android.volley.Response.Listener


class MainPresenter(private var view: MainActivity, private var model: Model) {

    init {
        getLeagues()
    }

    private fun getLeagues() {
        Model.mainManager.getLeagues(Listener
        { response ->
            onLeagueAvailable(response!!)
        })

    }

    private fun onLeagueAvailable(leaguesList: ArrayList<League>) {
        if (leaguesList.isEmpty()) {
            Model.mainManager.collectLeagues(
                Listener {
                    Log.d("Name1", it.isEmpty().toString())
                    view.completeSpinner(it)
                    Model.mainManager.setLeagueList(it)
                }
            )
        } else {
            view.completeSpinner(leaguesList);
            Model.mainManager.setLeagueList(leaguesList)
        }
    }

    fun getCountry(name: String): String {
        return Model.mainManager.getLeagueCountry(name)
    }

    fun getEndDate(name: String): String {
        return Model.mainManager.getLeagueEnd(name)
    }

    fun getInitDate(name: String): String {
        return Model.mainManager.getLeagueInit(name)
    }
}
