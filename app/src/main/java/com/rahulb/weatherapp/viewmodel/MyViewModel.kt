package com.rahulb.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulb.weatherapp.data.models.LocationData
import com.rahulb.weatherapp.data.models.ResponseWeather
import com.rahulb.weatherapp.data.repository.remote.WeatherRepository
import com.rahulb.weatherapp.utils.RequestCompleteListener
import com.rahulb.weatherapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MyViewModel : ViewModel() {

    private val tag = "ViewModel"

    //location live data
    val locationLiveData = MutableLiveData<com.rahulb.weatherapp.data.models.LocationData>()
    val locationLiveDataFailure = MutableLiveData<String>()

    //weatherByLocation live data
    val weatherByLocation = MutableLiveData<Resource<ResponseWeather>>()

    //weatherForecast live data
    val weatherForecast =
        MutableLiveData<Resource<com.rahulb.weatherapp.data.models.ResponseWeatherForecast>>()

    fun getCurrentLocation(model: com.rahulb.weatherapp.data.repository.local.LocationProvider) {
        model.getUserCurrentLocation(object :
            RequestCompleteListener<LocationData> {
            override fun onRequestCompleted(data: com.rahulb.weatherapp.data.models.LocationData) {
                locationLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String?) {
                locationLiveDataFailure.postValue(errorMessage)
            }
        })
    }

    /**
     * Weather by Location call
     */
    fun getWeatherByLocation(model: WeatherRepository, lat: String, lon: String) {
        viewModelScope.launch { safeWeatherByLocationFetch(model, lat, lon) }
    }

    private suspend fun safeWeatherByLocationFetch(
        model: WeatherRepository,
        lat: String,
        lon: String
    ) {
        weatherByLocation.postValue(Resource.loading(null))
        try {
            val response = model.getWeatherByLocation(lat, lon)
            weatherByLocation.postValue(handleWeatherResponse(response))
        } catch (t: Throwable) {
            when (t) {
                is IOException -> weatherByLocation.postValue(
                    Resource.error(
                        null,
                        "Network Failure"
                    )
                )
                else -> weatherByLocation.postValue(Resource.error(null, t.localizedMessage))
            }
        }
    }

    private fun handleWeatherResponse(response: Response<com.rahulb.weatherapp.data.models.ResponseWeather>): Resource<com.rahulb.weatherapp.data.models.ResponseWeather>? {
        return if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
            null,
            "Error: ${response.errorBody()}"
        )
    }

    /**
     * Weather Forecast call
     */
    fun getWeatherForecast(model: WeatherRepository, lat: String, lon: String, exclude: String) {
        viewModelScope.launch { safeWeatherForecastFetch(model, lat, lon, exclude) }
    }

    private suspend fun safeWeatherForecastFetch(
        model: WeatherRepository,
        lat: String,
        lon: String,
        exclude: String
    ) {
        weatherForecast.postValue(Resource.loading(null))
        try {
            val response = model.getWeatherForecast(lat, lon, exclude)
            weatherForecast.postValue(handleWeatherForecast(response))
        } catch (t: Throwable) {
            when (t) {
                is IOException -> weatherForecast.postValue(Resource.error(null, "Network Failure"))
                else -> weatherForecast.postValue(Resource.error(null, t.localizedMessage))
            }
        }
    }

    private fun handleWeatherForecast(response: Response<com.rahulb.weatherapp.data.models.ResponseWeatherForecast>): Resource<com.rahulb.weatherapp.data.models.ResponseWeatherForecast>? {
        return if (response.isSuccessful) Resource.success(response.body()) else Resource.error(
            null,
            "Error: ${response.errorBody()}"
        )
    }


}