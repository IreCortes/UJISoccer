package com.al376538.soccergame.squad

import android.util.Log
import com.al376538.soccergame.model.Model
import com.android.volley.Response

class SquadPresenter(private var view: SquadActivity, private var model: Model, private var teamId : Int) {

    init {
        getSquad()
    }

    private fun getSquad() {

        Model.squadManager.collectSquadPlayer(teamId,
            Response.Listener {
                view.setAdapter(it, model.squadManager.getNames())
                view.setCoach(model.squadManager.getCoach())
            }
        )
    }

}