package com.nrc7.carsalesapp.services

import android.util.Log
import com.nrc7.carsalesapp.models.Data
import com.nrc7.carsalesapp.models.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetServiceData {

    fun getDollarValues(callback: OnGetWrapperDataCallback) {
        RetrofitClient.instance.getWrapperData("2020-04-07")
            .enqueue(object : Callback<Wrapper> {
                override fun onResponse(call: Call<Wrapper>, response: Response<Wrapper>) {
                    Log.d("TAG", "CALL " + call.request().url())
                    val wrapperData = response.body()
                    if (wrapperData != null && response.isSuccessful && response.code() == 200) {
                        callback.onGetWrapperData(wrapperData.data)
                        Log.d("TAG", response.code().toString())
                        Log.d("TAG", "RESPONSE")
                    } else {
                        Log.d("TAG", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                    Log.d("TAG", "FAILURE")
                    Log.d("TAG", t.message)
                    Log.d("TAG", t.printStackTrace().toString())
                }
            })
    }

    interface OnGetWrapperDataCallback {
        fun onGetWrapperData(data: Data)
    }

}


