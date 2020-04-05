package com.al376538.soccergame.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.al376538.soccergame.R
import com.al376538.soccergame.team.StandingTeamActivity
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.League


class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainPresenter

    private lateinit var spinner: Spinner
    private lateinit var country: TextView
    private lateinit var start: TextView
    private lateinit var end: TextView
    private lateinit var btn: Button

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinner)
        country = findViewById(R.id.country)
        start = findViewById(R.id.start)
        end = findViewById(R.id.end)
        btn = findViewById(R.id.nextLayout)

        btn.text = "Accept"

        presenter = MainPresenter(
            this,
            Model.getInstanceModel(context = applicationContext)
        )

        btn.setOnClickListener {
            openActivity()
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.setCurrentLeague(parent!!.getItemAtPosition(position).toString())
                start.text = presenter.getInitDate()
                end.text = presenter.getEndDate()
                country.text = presenter.getCountry()
            }

        }
    }

    fun completeSpinner(leaguesNames: ArrayList<League>) {

        val names: ArrayList<String?> = ArrayList()

        for ((_, name) in leaguesNames) {
            names.add(name)
        }
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val listAdapterLeagues = ArrayAdapter(this, android.R.layout.simple_spinner_item, names)
        // Set layout to use when the list of choices appear
        listAdapterLeagues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner.setAdapter(listAdapterLeagues)
    }

    fun openActivity() {
        val intent = Intent(this, StandingTeamActivity::class.java)
        intent.putExtra(EXTRA_NAME, presenter.getLeagueId())
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        presenter.reset()
    }

}
