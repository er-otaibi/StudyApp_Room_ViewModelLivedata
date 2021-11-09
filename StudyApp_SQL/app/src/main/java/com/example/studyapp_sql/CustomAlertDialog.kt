package com.example.studyapp_sql

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

class CustomAlertDialog (fragment: Fragment, title: String, text: String) {
    init {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(fragment.context)

        // set message of alert dialog
        dialogBuilder.setMessage(text)
            // positive button text and action
            .setPositiveButton("OK", DialogInterface.OnClickListener {
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