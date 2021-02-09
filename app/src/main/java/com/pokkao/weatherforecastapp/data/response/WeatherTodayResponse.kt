package com.pokkao.weatherforecastapp.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WeatherTodayResponse(
    @SerializedName("current")
    var current: WeatherTodayCurrent? = null,
    @SerializedName("hourly")
    var hourly: List<WeatherTodayHourly>? = null,
    @SerializedName("lat")
    var lat: Double? = null,
    @SerializedName("lon")
    var lon: Double? = null,
    @SerializedName("timezone")
    var timezone: String? = null,
    @SerializedName("timezone_offset")
    var timezoneOffset: Int? = null
)

@Keep
data class WeatherTodayCurrent(
    @SerializedName("clouds")
    var clouds: Int? = null,
    @SerializedName("dew_point")
    var dewPoint: Double? = null,
    @SerializedName("dt")
    var dt: Int? = null,
    @SerializedName("feels_like")
    var feelsLike: Double? = null,
    @SerializedName("humidity")
    var humidity: Int? = null,
    @SerializedName("pressure")
    var pressure: Int? = null,
    @SerializedName("sunrise")
    var sunrise: Int? = null,
    @SerializedName("sunset")
    var sunset: Int? = null,
    @SerializedName("temp")
    var temp: Double? = null,
    @SerializedName("uvi")
    var uvi: Double? = null,
    @SerializedName("weather")
    var weather: List<WeatherTodayWeather>? = null,
    @SerializedName("wind_deg")
    var windDeg: Int? = null,
    @SerializedName("wind_speed")
    var windSpeed: Double? = null
)

@Keep
data class WeatherTodayHourly(
    @SerializedName("clouds")
    var clouds: Int? = null,
    @SerializedName("dew_point")
    var dewPoint: Double? = null,
    @SerializedName("dt")
    var dt: Long? = null,
    @SerializedName("feels_like")
    var feelsLike: Double? = null,
    @SerializedName("humidity")
    var humidity: Int? = null,
    @SerializedName("pressure")
    var pressure: Int? = null,
    @SerializedName("temp")
    var temp: Double? = null,
    @SerializedName("weather")
    var weather: List<WeatherTodayWeatherX>? = null,
    @SerializedName("wind_deg")
    var windDeg: Int? = null,
    @SerializedName("wind_speed")
    var windSpeed: Double? = null
)

@Keep
data class WeatherTodayWeather(
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
data class WeatherTodayWeatherX(
    @SerializedName("description")
    var description: String?,
    @SerializedName("icon")
    var icon: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("main")
    var main: String?
)
