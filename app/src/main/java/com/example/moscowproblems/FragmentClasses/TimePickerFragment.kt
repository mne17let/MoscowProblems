package com.example.moscowproblems.FragmentClasses

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.sql.Time
import java.util.*

private const val STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT_HOUR = "key for bundle with hour"
private const val STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT_MINUTE = "key for bundle with minutes"

private const val STRING_FOR_KEY_FOR_TARGET_TIME_FRAGMENT = "key time"

private const val TAG_FOR_TIME_DEBUG = "TimeDebug"

class TimePickerFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val currentCalendar = Calendar.getInstance()

        val currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = currentCalendar.get(Calendar.MINUTE)
        val currentSecond = currentCalendar.get(Calendar.SECOND)



        val newTimePicker = TimePickerDialog(requireContext(), listener, currentHour, currentMinute, true)

        Log.d(TAG_FOR_TIME_DEBUG, "Create time picker dialog " +
                "current time is $currentHour:$currentMinute")

        return newTimePicker
    }

    val listener = object : TimePickerDialog.OnTimeSetListener{
        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {


            val newBundleWithTime = Bundle()
            newBundleWithTime.apply {
                putInt(STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT_HOUR, hourOfDay)
                putInt(STRING_FOR_KEY_FOR_BUNDLE_FOR_TARGET_FRAGMENT_MINUTE, minute)
                Log.d(TAG_FOR_TIME_DEBUG, "Put time into bundle "+
                        "pick time is $hourOfDay:$minute")
            }

            parentFragmentManager.setFragmentResult("мой ключ времени", newBundleWithTime)
            Log.d(TAG_FOR_TIME_DEBUG, "Set fragment result from time picker fragment " +
                    "pick time is $hourOfDay:$minute")
        }

    }


}