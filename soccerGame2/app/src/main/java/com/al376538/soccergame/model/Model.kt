package com.al376538.soccergame.model


import android.content.Context
import androidx.room.Room.databaseBuilder
import com.al376538.soccergame.main.MainManager
import com.al376538.soccergame.model.database.DAO
import com.al376538.soccergame.model.database.DataBase
import com.al376538.soccergame.team.StandingsManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

object Model {
    internal lateinit var dao: DAO
    internal lateinit var queue: RequestQueue

    lateinit var mainManager: MainManager
    lateinit var standingsManager: StandingsManager

    //Model create
    private fun model(context: Context) {
        val database: DataBase = databaseBuilder(
            context,
            DataBase::class.java,
            "Database"
        ).build()

        dao = database.Dao()
        queue = Volley.newRequestQueue(context)
        mainManager = MainManager(this)
        standingsManager = StandingsManager(this)
    }

    //Get an instance of a model
    fun getInstanceModel(context: Context): Model {
        model(context)
        return this
    }
}