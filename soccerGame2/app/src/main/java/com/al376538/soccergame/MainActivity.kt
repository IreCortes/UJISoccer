package com.al376538.soccergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    lateinit var spinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinner)
    }

    fun completeSpinner(leaguesNames: ArrayList<String?>) {



            // Create an ArrayAdapter using a simple spinner layout and languages array
            val listAdapterLeagues = ArrayAdapter(this, android.R.layout.simple_spinner_item, leaguesNames)
            // Set layout to use when the list of choices appear
        listAdapterLeagues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set Adapter to Spinner
            spinner!!.setAdapter(listAdapterLeagues)
    }

}
