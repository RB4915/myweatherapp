package com.rahulb.weatherapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    suspend fun getWeatherByLocation(
        @Query("lat")
        latitude: String,
        @Query("lon")
        longitude: String
    ): Response<com.rahulb.weatherapp.data.models.ResponseWeather>

    @GET("onecall")
    suspend fun getWeatherForecast(
        @Query("lat")
        latitude: String,
        @Query("lon")
        longitude: String,
        @Query("exclude")
        exclude: String
    ): Response<com.rahulb.weatherapp.data.models.ResponseWeatherForecast>
}