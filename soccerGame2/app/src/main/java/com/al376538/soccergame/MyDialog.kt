package com.al376538.soccergame

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment

@Suppress("UNREACHABLE_CODE")
class MyDialog : AppCompatDialogFragment() {

    private lateinit var founded : TextView
    private lateinit var shortName : TextView
    private lateinit var stadium : TextView
    private lateinit var clubcolors : TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater : LayoutInflater = activity!!.layoutInflater
        val view : View = inflater.inflate(R.layout.cuadro_dialogo, null)


        builder.setView(view).setTitle("Login")
            // positive button text and action
            .setPositiveButton("WEB", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
            // negative button text and action
            .setNegativeButton("OKAY", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
            .setNeutralButton("NEXT MATCHES", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        founded = view.findViewById(R.id.founded)
        shortName = view.findViewById(R.id.shortname)
        stadium = view.findViewById(R.id.stadium)
        clubcolors = view.findViewById(R.id.clubcolor)



        return builder.create()
    }
}