package com.rahulb.weatherapp.data.repository.remote

import com.rahulb.weatherapp.network.RetrofitClient

class WeatherRepository {

    suspend fun getWeatherByLocation(lat: String, lon: String) =
        RetrofitClient.api.getWeatherByLocation(lat, lon)

    suspend fun getWeatherForecast(lat: String, lon: String, exclude: String) =
        RetrofitClient.api.getWeatherForecast(lat, lon, exclude)
}