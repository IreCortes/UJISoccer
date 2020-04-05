package com.al376538.soccergame.squad

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.al376538.soccergame.R

class AdapterListViewSquad (private val context: Activity, private val arrayStanding : ArrayList<SquadPlayer>, names : Array<String>)
    : ArrayAdapter<String>(context, R.layout.list_content, names) {

    override fun getView(i : Int, view: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_squad_content, null, true)

        val name        = rowView.findViewById(R.id.nameTeam) as TextView
        val position    = rowView.findViewById(R.id.position) as TextView

        name.text       = arrayStanding[i].name
        position.text       = arrayStanding[i].position

        return rowView
    }
}