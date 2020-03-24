package com.al376538.soccergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.al376538.soccergame.Model

class MainActivity : AppCompatActivity() {

    lateinit var spinner : Spinner
    lateinit var presenter : Presenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinner)
        presenter = Presenter(this, instanceModel.getInstanceModel(context = applicationContext))
    }

    fun completeSpinner(leaguesNames: ArrayList<String?>) {
            // Create an ArrayAdapter using a simple spinner layout and languages array
            val listAdapterLeagues = ArrayAdapter(this, android.R.layout.simple_spinner_item, leaguesNames)
            // Set layout to use when the list of choices appear
        listAdapterLeagues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set Adapter to Spinner
            spinner.setAdapter(listAdapterLeagues)
    }

}
