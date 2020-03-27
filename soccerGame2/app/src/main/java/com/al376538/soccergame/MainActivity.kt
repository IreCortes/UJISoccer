package com.al376538.soccergame

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.al376538.soccergame.database.League


class MainActivity : AppCompatActivity() {

    private lateinit var spinner : Spinner
    private lateinit var presenter : Presenter

    private lateinit var country : TextView
    private lateinit var start : TextView
    private lateinit var end : TextView
    private lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Creado", "OnCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinner)
        country = findViewById(R.id.country)
        start = findViewById(R.id.start)
        end = findViewById(R.id.end)
        btn = findViewById(R.id.nextLayout)

        btn.text = "Accept"

        presenter = Presenter(this, Model.getInstanceModel(context = applicationContext))

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //country.text = parent!!.getItemAtPosition(position).toString()
                start.text = presenter.getInitDate(parent!!.getItemAtPosition(position).toString())
                end.text = presenter.getEndDate(parent!!.getItemAtPosition(position).toString())
                country.text = presenter.getCountry(parent!!.getItemAtPosition(position).toString())
            }

        }

        /*spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) { // your code here
                //Log.d("Name", parentView?.getItemAtPosition(position).toString());
                //getLeagueComponent(parentView?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) { // your code here
                country.text = ""
                start.text = ""
                end.text = ""
            }
        }*/

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

    /*private fun getLeagueComponent(name: String) {

        Log.d("Error", "HE LLEGADO")


        country.text = presenter.getCountry(name)

    }*/
}
