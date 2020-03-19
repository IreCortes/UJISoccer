package com.al376538.soccergame

import com.al376538.soccergame.database.League

class Presenter {

    var model : Model = TODO()
    var view : MainActivity
    var leagues : Array<League>

    fun Presenter () {
        if(model.getLeagues() == null)
            leagues = model.getLeagues()
    }

}