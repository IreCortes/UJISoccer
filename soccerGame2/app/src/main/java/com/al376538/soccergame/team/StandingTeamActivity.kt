package com.al376538.soccergame.team

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.al376538.soccergame.R
import com.al376538.soccergame.main.MainActivity.Companion.EXTRA_NAME
import com.al376538.soccergame.model.Model
import com.al376538.soccergame.standings.AdapterListView
import com.al376538.soccergame.standings.TeamInStanding

class StandingTeamActivity : AppCompatActivity() {

    private lateinit var presenter: StandingTeamPresenter
    private lateinit var adapter: AdapterListView
    private lateinit var myListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standings)

        val extra = intent.extras
        val leagueID : String = extra!!.getString(EXTRA_NAME)!!

        myListView = findViewById(R.id.listView) as ListView

        presenter = StandingTeamPresenter(this, Model.getInstanceModel(context = applicationContext), leagueID)

        myListView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {

                // value of item that is clicked
                val itemValue = myListView.getItemAtPosition(position) as String

                // Toast the values
                showDialog("ahjsdajsd", "jahsfdjhd")
            }
        }

    }

    fun setAdapter(array : ArrayList<TeamInStanding>, positions: Array<String>) {
        adapter = AdapterListView(
            this,
            array,
            positions
        )
        myListView.adapter = adapter
    }

    fun showDialog(message : String, title : String) {
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage("Do you want to close this application ?")
            // if the dialog is cancelable
            .setCancelable(true)
            // positive button text and action
            .setPositiveButton("WEB", DialogInterface.OnClickListener {
                    dialog, id -> finish()
            })
            // negative button text and action
            .setNegativeButton("OKAY", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
            .setNeutralButton("NEXT MATCHES", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(title)
        // show alert dialog
        alert.show()
    }
}
