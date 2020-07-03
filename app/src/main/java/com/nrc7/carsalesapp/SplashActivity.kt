package com.nrc7.carsalesapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
}