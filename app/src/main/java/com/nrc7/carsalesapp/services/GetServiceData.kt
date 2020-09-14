package com.nrc7.carsalesapp.services

import android.util.Log
import com.nrc7.carsalesapp.models.Data
import com.nrc7.carsalesapp.models.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetServiceData {

    // This method makes the call (async) to API and obtains response data from service
    fun getWrapperData(callback: OnGetWrapperDataCallback, date: String) {
        // Executing async call
        RetrofitClient.instance.getWrapperData(date)
            .enqueue(object : Callback<Wrapper> {

                override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                    Log.d("TAG", "RESPONSE")
                    Log.d("TAG", "CALL: " + call.request().url())
                    // Response data
                    val wrapperData = response.body()
                    // Status validation
                    if (wrapperData != null && response.isSuccessful && response.code() == 200) {
                        // Send the data valid object to subscribers (MainActivity)
                        callback.onGetWrapperData(wrapperData.data)
                        Log.d("TAG", "Succesful: " + response.code().toString())
                    } else {
                        Log.d("TAG", "Not succesful: " + response.code().toString())
                        call.request().url().toString()
                    }
                }

                override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                    Log.d("TAG", "FAILURE")
                    Log.d("TAG", t.message)
                    Log.d("TAG", t.printStackTrace().toString())
                }
            })
    }

    // Inner callback Send a data object to any subscriber
    interface OnGetWrapperDataCallback {
        fun onGetWrapperData(data: Data)
    }

}


