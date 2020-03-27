package com.al376538.soccergame.main

import android.util.Log
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.League
import com.android.volley.Response.Listener


class mainPresenter(private var view: MainActivity, private var model: Model) {

    init {
        getLeagues()
    }

    private fun getLeagues() {
        Model.getLeagues(Listener
        { response ->
            onLeagueAvailable(response!!)
        })

    }

    private fun onLeagueAvailable(leaguesList: ArrayList<League>) {
        if (leaguesList.isEmpty()) {
            Model.collectLeagues(
                Listener {
                    Log.d("Name1", it.isEmpty().toString())
                    view.completeSpinner(it)
                    Model.setLeagueList(it)
                }
            )
        } else {
            view.completeSpinner(leaguesList);
            Model.setLeagueList(leaguesList)
        }
    }

    fun getCountry(name: String): String {
        return Model.getLeagueCountry(name)
    }

    fun getEndDate(name: String): String {
        return Model.getLeagueEnd(name)
    }

    fun getInitDate(name: String): String {
        return Model.getLeagueInit(name)
    }
}
