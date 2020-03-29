package com.al376538.soccergame.team

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.al376538.soccergame.R
import kotlinx.android.synthetic.main.list_content.view.*

class AdapterListView (private val context: Activity, private val arrayStanding : ArrayList<TeamInStanding>)
    : ArrayAdapter<String>(context, R.layout.list_content){

    override fun getView(i : Int, view: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_content, null, true)

        val position    = rowView.findViewById(R.id.position) as TextView
        val team_name   = rowView.findViewById(R.id.team_name) as TextView
        val playedGames = rowView.findViewById(R.id.playedGames) as TextView
        val win         = rowView.findViewById(R.id.win) as TextView
        val draw        = rowView.findViewById(R.id.draw) as TextView
        val lost        = rowView.findViewById(R.id.loss) as TextView
        val ptos        = rowView.findViewById(R.id.ptos) as TextView
        val goalsF      = rowView.findViewById(R.id.goalsFavor) as TextView
        val goalsA      = rowView.findViewById(R.id.goalsAgainst) as TextView

        position.text       = arrayStanding[i].position.toString()
        team_name.text      = arrayStanding[i].team.getString("name").toString()
        playedGames.text    = arrayStanding[i].playedGames.toString()
        win.text            = arrayStanding[i].won.toString()
        draw.text           = arrayStanding[i].draw.toString()
        lost.text           = arrayStanding[i].loss.toString()
        ptos.text           = arrayStanding[i].points.toString()
        goalsF.text         = arrayStanding[i].goalsFor.toString()
        goalsA.text         = arrayStanding[i].goalsAgainst.toString()

        return rowView
    }

}

/*class AdapterListView(private val context: Activity, private val title: Array<String>, private val description: Array<String>, private val imgid: Array<Int>)
    : ArrayAdapter<String>(context, R.layout.list_content, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView

        titleText.text = title[position]
        imageView.setImageResource(imgid[position])
        subtitleText.text = description[position]

        return rowView
    }
}*/