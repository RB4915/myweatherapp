package com.rahulb.weatherapp.data.repository.local

import com.rahulb.weatherapp.data.models.LocationData
import com.rahulb.weatherapp.utils.RequestCompleteListener

interface LocationProviderInterface {
    fun getUserCurrentLocation(callback: RequestCompleteListener<LocationData>)
}