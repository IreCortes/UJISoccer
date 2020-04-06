package com.al376538.soccergame.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.al376538.soccergame.R
import com.al376538.soccergame.squad.SquadActivity
import com.al376538.soccergame.team.StandingTeamActivity


class MyDialog : AppCompatDialogFragment() {

    private lateinit var founded : TextView
    private lateinit var shortName : TextView
    private lateinit var stadium : TextView
    private lateinit var clubcolors : TextView

    private var _founded : String = ""
    private var _shortName : String = ""
    private var _stadium : String = ""
    private var _clubColors : String = ""
    private var title : String = ""
    private var idTeam : Int = 0
    private var url : String = ""


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater : LayoutInflater = activity!!.layoutInflater
        val view : View = inflater.inflate(R.layout.cuadro_dialogo, null)


        builder.setView(view).setTitle(title)
            // positive button text and action
            .setPositiveButton("WEB", DialogInterface.OnClickListener {
                    dialog, id -> getURL()
            })
            // negative button text and action
            .setNegativeButton("OKAY", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
            .setNeutralButton("PLAYERS", DialogInterface.OnClickListener {
                    dialog, id -> openActivity()
            })

        founded = view.findViewById(R.id.founded)
        shortName = view.findViewById(R.id.shortname)
        stadium = view.findViewById(R.id.stadium)
        clubcolors = view.findViewById(R.id.clubcolor)

        founded.text = _founded
        shortName.text = _shortName
        stadium.text = _stadium
        clubcolors.text = _clubColors



        return builder.create()
    }

    fun setTexts(f : String, sN : String, s : String, c : String, t :String) {
        _founded = f
        _shortName = sN
        _stadium = s
        _clubColors = c
        title = t
    }

    fun setIdTeam(id : Int){
        idTeam = id
    }

    fun setURL(a: String) {
        url = a
    }

    private fun openActivity() {
        val intent = Intent(this.context, SquadActivity::class.java)
        intent.putExtra(StandingTeamActivity.TEAM_ID, idTeam.toString())
        startActivity(intent)
    }

    private fun getURL() {
        try {
            val webpage: Uri = Uri.parse(url)
            val myIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(myIntent)
        } catch (e: ActivityNotFoundException) {
        }
    }

}