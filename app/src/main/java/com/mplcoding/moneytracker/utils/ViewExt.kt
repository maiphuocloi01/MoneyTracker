package com.mplcoding.moneytracker.utils

import android.app.DatePickerDialog
import android.content.Context
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

fun TextInputEditText.transformIntoDatePicker(
    context: Context,
    format: String,
    maxDate: Date? = null
) {

    val myCalendar = Calendar.getInstance()
    val datePickerOnDataListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, monthOfYear)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val sdf = SimpleDateFormat(format, Locale.UK)
        setText(sdf.format(myCalendar.time))
    }

    setOnClickListener {
        DatePickerDialog(
            context,
            datePickerOnDataListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).run {
            maxDate?.time?.also {
                datePicker.maxDate = it
            }
            show()
        }
    }
}