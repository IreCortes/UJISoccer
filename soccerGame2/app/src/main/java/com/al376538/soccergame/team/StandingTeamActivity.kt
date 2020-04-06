package com.al376538.soccergame.team

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.al376538.soccergame.MyDialog
import com.al376538.soccergame.R
import com.al376538.soccergame.main.MainActivity.Companion.EXTRA_NAME
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.squad.SquadActivity
import com.al376538.soccergame.standings.AdapterListView
import com.al376538.soccergame.standings.TeamInStanding

class StandingTeamActivity : AppCompatActivity() {

    private lateinit var presenter: StandingTeamPresenter
    private lateinit var adapter: AdapterListView
    private lateinit var myListView: ListView
    private lateinit var idTeam: String
    private lateinit var progressBar: ProgressBar

    companion object {
        const val TEAM_ID = "TEAM_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standings)

        val extra = intent.extras
        val leagueID : String = extra!!.getString(EXTRA_NAME)!!

        progressBar = findViewById(R.id.progressBar)
        //progressBar.visibility = View.VISIBLE

        myListView = findViewById(R.id.listView)

        presenter = StandingTeamPresenter(this, Model.getInstanceModel(context = applicationContext), leagueID)

        myListView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                // value of item that is clicked
                val itemValue = myListView.getItemAtPosition(position) as String
                idTeam = itemValue
                // Toast the values
                openDialog(itemValue)
            }

    }

    override fun onStart() {
        super.onStart()
    }

    fun setAdapter(array : ArrayList<TeamInStanding>, positions: Array<String>) {
        adapter = AdapterListView(this, array, positions)
        myListView.adapter = adapter
        progressBar.visibility = View.GONE
    }

    private fun openDialog(itemValue: String) {
        val myDialog = MyDialog()
        myDialog.setIdTeam(presenter.getIdTeam(itemValue))
        myDialog.setTexts( presenter.getYearFounded(itemValue).toString(), presenter.getShort(itemValue).toString(),
            presenter.getStadium(itemValue).toString(), presenter.getColorsClub(itemValue).toString(), presenter.getTeamName(itemValue))
        myDialog.show(supportFragmentManager, "example Dialog")
    }

}
