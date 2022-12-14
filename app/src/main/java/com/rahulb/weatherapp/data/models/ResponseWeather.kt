package com.rahulb.weatherapp.data.models
import com.google.gson.annotations.SerializedName

data class ResponseWeather(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: com.rahulb.weatherapp.data.models.Clouds,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: com.rahulb.weatherapp.data.models.Coord,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: com.rahulb.weatherapp.data.models.Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("sys")
    val sys: com.rahulb.weatherapp.data.models.Sys,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<com.rahulb.weatherapp.data.models.Weather>,
    @SerializedName("wind")
    val wind: com.rahulb.weatherapp.data.models.Wind
)

data class Clouds(
    @SerializedName("all")
    val all: Int
)

data class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("grnd_level")
    val grndLevel: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)

data class Sys(
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int
)

data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)

data class Wind(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("speed")
    val speed: Double
)