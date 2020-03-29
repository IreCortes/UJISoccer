package com.al376538.soccergame.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.al376538.soccergame.R
import com.al376538.soccergame.main.MainActivity.Companion.EXTRA_NAME
import com.al376538.soccergame.model.Model

class StandingsActivity : AppCompatActivity() {

    private lateinit var presenter: StandingsPresenter
    private lateinit var adapter: AdapterListView
    private lateinit var myListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standings)

        val extra = intent.extras
        val leagueID : String = extra!!.getString(EXTRA_NAME)!!

        myListView = findViewById(R.id.listView) as ListView

        presenter = StandingsPresenter(this, Model.getInstanceModel(context = applicationContext), leagueID)

    }

    fun setAdapter(array : ArrayList<TeamInStanding>, positions: Array<String>) {
        adapter = AdapterListView(this, array, positions)
        myListView.adapter = adapter
    }
}
