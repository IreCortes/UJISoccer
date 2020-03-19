package com.al376538.soccergame

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TeamActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        var array = arrayOf("Melbourne", "Vienna", "Vancouver", "Toronto", "Calgary", "Adelaide", "Perth", "Auckland", "Helsinki", "Hamburg", "Munich", "New York", "Sydney", "Paris", "Cape Town", "Barcelona", "London", "Bangkok")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val adapter = ArrayAdapter(this,
            R.layout.post, array)

        val listView:ListView = findViewById(R.id.listview_1)
        listView.setAdapter(adapter)

        //esto sirve para cargar algo cuando pulsas un elemento de la vista.
        listView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {

                // value of item that is clicked
                val itemValue = listView.getItemAtPosition(position) as String

                // Toast the values
                Toast.makeText(applicationContext,
                        "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}