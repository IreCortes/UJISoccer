package com.al376538.soccergame.squad

import android.util.Log
import com.al376538.soccergame.dialogs.PlayerDialog
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

    private fun getData(pos : String): SquadPlayer{
        return model.squadManager.getPlayer(pos)
    }

    fun getA(pos: String): String{
        return model.squadManager.getDateBirthDay(getData(pos))
    }
    fun getB(pos: String): String {
       return model.squadManager.getcountryBirthDay(getData(pos))
    }
    fun getC(pos: String): String{
        return model.squadManager.getNationality(getData(pos))
    }
    fun getD(pos: String): String {
        return model.squadManager.getNumber(getData(pos))
    }

}