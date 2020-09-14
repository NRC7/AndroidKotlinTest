package com.nrc7.carsalesapp.views

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.nrc7.carsalesapp.R
import com.nrc7.carsalesapp.models.Data
import com.nrc7.carsalesapp.services.GetServiceData
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity(), GetServiceData.OnGetWrapperDataCallback, DatePickerDialog.OnDateSetListener{

    // Service reference
    var getServiceData : GetServiceData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init service
        getServiceData = GetServiceData()
        //
        getServiceData!!.getWrapperData(this, "manzana")


    }

    // DateBtn onClick event
    fun ShowDatePicker(view: View) {
        showDatePicker()
    }

    // Service response (async)
    override fun onGetWrapperData(data: Data) {
        fecthDateToView(data)
    }

    // Parse response's date to format
    private fun headerDateParser(date: String): String {
        val fmt = SimpleDateFormat("yyyy-MM-dd")
        val auxDate: Date = fmt.parse(date)
        val fmtOut = SimpleDateFormat("d 'de' MMMM 'de' yyyy", Locale("es", "ES"))
        return fmtOut.format(auxDate)
    }

    // Get day before date
    private fun getDefaultDate(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        return dateFormat.format(cal.time)
    }

    // Fetch Data to Textviews
    private fun fecthDateToView(data: Data) {
        dateTv.text = headerDateParser(data.date)
        casesTv.text = data.confirmed.toString()
        deceasedTv.text = data.deaths.toString()
    }

    // Date picker
    private fun showDatePicker() {
        DatePickerDialog(this,
            this,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()
    }

    // Delete reference to prevent memory leaks
    override fun onDestroy() {
        super.onDestroy()
        getServiceData = null
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // 2020-04-07
        var date = year.toString() + "-"
        val currentMonth = month
        if (currentMonth.toString().length < 2) {
            date += "0" + currentMonth + "-"
        } else {
            date += currentMonth.toString() + "-"
        }
        if (dayOfMonth.toString().length < 2) {
            date += "0" + dayOfMonth
        } else {
            date += dayOfMonth
        }
        Log.d("TAG", date)
        getServiceData!!.getWrapperData(this, date)
    }
}
