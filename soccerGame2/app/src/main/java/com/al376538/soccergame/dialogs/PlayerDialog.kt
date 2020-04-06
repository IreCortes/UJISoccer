package com.al376538.soccergame.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDialogFragment
import com.al376538.soccergame.R

class PlayerDialog : AppCompatDialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater : LayoutInflater = activity!!.layoutInflater
        val view : View = inflater.inflate(R.layout.cuadro_dialogo, null)
        return super.onCreateDialog(savedInstanceState)
    }

}