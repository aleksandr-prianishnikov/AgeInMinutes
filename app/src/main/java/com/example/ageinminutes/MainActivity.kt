package com.example.ageinminutes


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
                .setOnClickListener {view ->
                    clickDatePicker(view)
                }
    }

    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            View, selectedYear, selectedMonth, selectedDayOfMonth ->
//            Toast.makeText(this,
//                    "The chosen year is $selectedYear, the month is ${selectedMonth + 1} and the day is $selectedDayOfMonth"
//                    , Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            val tvSelected = findViewById<TextView>(R.id.tvSelectedDate).setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time / 60000 // переводим миллисекунды в минуты (1000 * 60)

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes = currentDate!!.time / 60000

            val differentInMinutes = currentDateInMinutes - selectedDateInMinutes

            val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                    .setText(differentInMinutes.toString())
        }
                ,year
                ,month
                ,day)

        dpd.datePicker.setMaxDate(Date().time) // делаем ограничения в выборе даты(нельзя выбрать дату больше, чем сегодня)
        dpd.show()
    }

}