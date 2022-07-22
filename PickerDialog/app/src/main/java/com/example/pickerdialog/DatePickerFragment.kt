package com.example.pickerdialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        (activity as MainActivity).findViewById<TextView>(R.id.date_picker_text).text =
            getString(R.string.date_text, year, month, day)
    }
}