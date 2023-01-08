package com.centrefx.idcardinfo_k

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class WarningDialog(private val msg: String) : DialogFragment() {

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        val ad: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        ad.setTitle("Error!")
        ad.setCancelable(true)
        ad.setMessage(msg)
        ad.setPositiveButton("") { _: DialogInterface, _: Int -> }
        return ad.create()
    }
}