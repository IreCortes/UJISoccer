package com.al376538.soccergame

import com.al376538.soccergame.database.League
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener


class Presenter {

    var model : Model = TODO()
    var view : MainActivity
    var leagues : Array<League>

    fun Presenter () {
        getLeagues()
    }

    fun getLeagues() {
        if(model.getLeagues().isEmpty()) {
                model.collectLeagues(
                    Listener<List<League?>?> { leagues
                        -> //lo que quiera hacer cuando haya funcionado
                    },
                    ErrorListener { error ->
                        //AÑADIR UN TOAST? view.showError(error.message)
                    })

            }
        }
        else {

    }


}