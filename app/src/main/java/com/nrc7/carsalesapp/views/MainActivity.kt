package com.nrc7.carsalesapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nrc7.carsalesapp.R
import com.nrc7.carsalesapp.models.Data
import com.nrc7.carsalesapp.services.GetServiceData
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), GetServiceData.OnGetWrapperDataCallback{

    // Service reference
    var getServiceData : GetServiceData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init service
        getServiceData = GetServiceData()
        getServiceData!!.getDollarValues(this)
    }

    // Service response (async)
    override fun onGetWrapperData(data: Data) {
        fecthDateToView(data)
    }

    // Parse response's date to format
    private fun dateParser(date: String): String {
        val fmt = SimpleDateFormat("yyyy-MM-dd")
        val auxDate: Date = fmt.parse(date)
        val fmtOut = SimpleDateFormat("d 'de' MMMM 'de' yyyy", Locale("es", "ES"))
        return fmtOut.format(auxDate)
    }

    // Fetch Data to Textviews
    private fun fecthDateToView(data: Data) {
        dateTv.text = dateParser(data.date)
        casesTv.text = data.confirmed.toString()
        deceasedTv.text = data.deaths.toString()
    }

    // Delete reference to prevent memory leaks
    override fun onDestroy() {
        super.onDestroy()
        getServiceData = null
    }
}
