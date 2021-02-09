package com.pokkao.weatherforecastapp.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CurrentWeatherResponse(
    @SerializedName("base")
    var base: String? = null,
    @SerializedName("clouds")
    var clouds: CurrentWeatherClouds? = null,
    @SerializedName("cod")
    var cod: Int? = null,
    @SerializedName("coord")
    var coord: CurrentWeatherCoord? = null,
    @SerializedName("dt")
    var dt: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("main")
    var main: CurrentWeatherMain? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("sys")
    var sys: CurrentWeatherSys? = null,
    @SerializedName("visibility")
    var visibility: Int? = null,
    @SerializedName("weather")
    var weather: List<CurrentWeatherWeather>? = null,
    @SerializedName("wind")
    var wind: CurrentWeatherWind? = null
)

@Keep
data class CurrentWeatherClouds(
    @SerializedName("all")
    var all: Int? = null
)

@Keep
data class CurrentWeatherCoord(
    @SerializedName("lat")
    var lat: Double? = null,
    @SerializedName("lon")
    var lon: Double? = null
)

@Keep
data class CurrentWeatherMain(
    @SerializedName("humidity")
    var humidity: Int? = null,
    @SerializedName("pressure")
    var pressure: Int? = null,
    @SerializedName("temp")
    var temp: Double? = null,
    @SerializedName("temp_max")
    var tempMax: Double? = null,
    @SerializedName("temp_min")
    var tempMin: Double? = null
)

@Keep
data class CurrentWeatherSys(
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("message")
    var message: Double? = null,
    @SerializedName("sunrise")
    var sunrise: Int? = null,
    @SerializedName("sunset")
    var sunset: Int? = null,
    @SerializedName("type")
    var type: Int? = null
)

@Keep
data class CurrentWeatherWeather(
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("main")
    var main: String? = null
)

@Keep
data class CurrentWeatherWind(
    @SerializedName("deg")
    var deg: Int? = null,
    @SerializedName("speed")
    var speed: Double? = null
)
