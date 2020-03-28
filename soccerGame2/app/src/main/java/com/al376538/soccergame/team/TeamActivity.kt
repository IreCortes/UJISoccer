package com.al376538.soccergame.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.al376538.soccergame.R
import com.al376538.soccergame.main.MainActivity.Companion.EXTRA_NAME

class TeamActivity : AppCompatActivity() {

    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        val extra = intent.extras
        val leagueID : String = extra!!.getString(EXTRA_NAME)!!
        textView = findViewById(R.id.prueba)
        textView.text = leagueID
    }
}
