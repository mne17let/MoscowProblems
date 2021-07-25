package com.example.moscowproblems.FragmentClasses

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val argumentForCurrentDialogFragment = arguments?.getSerializable("Дата текущей проблемы")

        val currentCalendar = Calendar.getInstance()

        currentCalendar.time = argumentForCurrentDialogFragment as Date

        val currentYear = currentCalendar.get(Calendar.YEAR)
        val currentMonth = currentCalendar.get(Calendar.MONTH)
        val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)

        val dialogForReturn = DatePickerDialog(requireContext(), null, currentYear, currentMonth, currentDay)

        return dialogForReturn
    }
}