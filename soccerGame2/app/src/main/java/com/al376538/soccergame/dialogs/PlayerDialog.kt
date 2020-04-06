package com.al376538.soccergame.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialogFragment
import com.al376538.soccergame.R

class PlayerDialog : AppCompatDialogFragment(){

    private lateinit var _dateBirthday: TextView
    private lateinit var _countryBirthday: TextView
    private lateinit var _nationality: TextView
    private lateinit var _shirtNumber: TextView

    private var dateBirthday: String = ""
    private var countryBirthday: String = ""
    private var nationality: String = ""
    private var shirtNumber: String = ""
    private var title: String = ""


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater : LayoutInflater = activity!!.layoutInflater
        val view : View = inflater.inflate(R.layout.dialog_player, null)

        builder.setView(view).setTitle(title) .setPositiveButton("OKAY", DialogInterface.OnClickListener {
                dialog, id -> dialog.cancel()
        })

        _dateBirthday = view.findViewById(R.id.dateB)
        _countryBirthday = view.findViewById(R.id.countryB)
        _nationality = view.findViewById(R.id.nationality)
        _shirtNumber = view.findViewById(R.id.number)

        _dateBirthday.text = dateBirthday
        _countryBirthday.text = countryBirthday
        _nationality.text = nationality
        _shirtNumber.text = shirtNumber

        return builder.create()
    }

    fun setText(dB: String, cB: String, nt: String, sN: String) {

        dateBirthday = dB
        countryBirthday = cB
        nationality = nt
        shirtNumber = sN
    }

}