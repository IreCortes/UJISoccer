package com.al376538.soccergame.squad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.al376538.soccergame.R
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.team.StandingTeamActivity

class SquadActivity : AppCompatActivity() {

    private lateinit var presenter: SquadPresenter
    private lateinit var nameCoach : TextView
    private lateinit var adapter: AdapterListViewSquad
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squad)


        val extra = intent.extras
        val teamID : String = extra!!.getString(StandingTeamActivity.TEAM_ID)!!

        nameCoach = findViewById(R.id.name_coach)
        listView = findViewById(R.id.listViewSquad)

        presenter = SquadPresenter(this, Model.getInstanceModel(context = applicationContext), teamID.toInt())

        nameCoach.text = presenter.getCoach()

    }

    fun setAdapter(array : ArrayList<SquadPlayer>, names: Array<String>) {

        adapter = AdapterListViewSquad(this, array, names)
        listView.adapter = adapter
    }
}
