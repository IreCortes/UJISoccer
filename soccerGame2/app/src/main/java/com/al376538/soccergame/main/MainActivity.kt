package com.al376538.soccergame.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.al376538.soccergame.R
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.model.database.League
import com.al376538.soccergame.team.TeamActivity


class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var presenter: mainPresenter

    private lateinit var country: TextView
    private lateinit var start: TextView
    private lateinit var end: TextView
    private lateinit var btn: Button
    private lateinit var prueba : TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinner)
        country = findViewById(R.id.country)
        start = findViewById(R.id.start)
        end = findViewById(R.id.end)
        btn = findViewById(R.id.nextLayout)
        prueba = findViewById(R.id.prueba)

        btn.text = "Accept"


        presenter = mainPresenter(
            this,
            Model.getInstanceModel(context = applicationContext)
        )
        prueba.text = "aiuwhdaierw"

        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val changePage = Intent(this@MainActivity, TeamActivity :: class.java)
                startActivity(changePage)
            }
        })
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //country.text = parent!!.getItemAtPosition(position).toString()
                start.text = presenter.getInitDate(parent!!.getItemAtPosition(position).toString())
                end.text = presenter.getEndDate(parent.getItemAtPosition(position).toString())
                country.text = presenter.getCountry(parent.getItemAtPosition(position).toString())
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
}
