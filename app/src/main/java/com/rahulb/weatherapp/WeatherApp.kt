package com.rahulb.weatherapp

import android.app.Application
import android.content.Context

class WeatherApp : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        Companion.context = applicationContext
    }
}