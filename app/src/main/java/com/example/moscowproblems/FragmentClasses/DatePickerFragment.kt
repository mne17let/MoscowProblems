package com.example.moscowproblems.FragmentClasses

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val STRING_FOR_KEY_FOR_TARGET_FRAGMENT = "key"
private const val STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT = "key for bundle"

class DatePickerFragment: DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dateSetListener = MyObject()

        val argumentForCurrentDialogFragment = arguments?.getSerializable("Дата текущей проблемы")

        val currentCalendar = Calendar.getInstance()

        currentCalendar.time = argumentForCurrentDialogFragment as Date

        val currentYear = currentCalendar.get(Calendar.YEAR)
        val currentMonth = currentCalendar.get(Calendar.MONTH)
        val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)

        val dialogForReturn = DatePickerDialog(requireContext(), dateSetListener, currentYear, currentMonth, currentDay)

        return dialogForReturn
    }

    inner class MyObject: DatePickerDialog.OnDateSetListener{
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            val newDate = GregorianCalendar(year, month, dayOfMonth).time
            sendNewDate(newDate)
        }

    }

    fun sendNewDate(date: Date){
        val bundleWithNewDate = Bundle()
        bundleWithNewDate.putSerializable(STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT, date)
        parentFragmentManager.setFragmentResult(STRING_FOR_KEY_FOR_TARGET_FRAGMENT, bundleWithNewDate)
    }
}