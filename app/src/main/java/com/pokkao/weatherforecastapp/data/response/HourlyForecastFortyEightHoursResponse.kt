package com.pokkao.weatherforecastapp.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HourlyForecastFortyEightHoursResponse(
    @SerializedName("hourly")
    var hourly: List<HistoricalWeatherHourly>? = null,
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
data class HistoricalWeatherHourly(
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
    @SerializedName("pop")
    var pop: Double? = null,
    @SerializedName("pressure")
    var pressure: Int? = null,
    @SerializedName("rain")
    var rain: HistoricalWeatherRain? = null,
    @SerializedName("snow")
    var snow: HistoricalWeatherSnow? = null,
    @SerializedName("temp")
    var temp: Double? = null,
    @SerializedName("uvi")
    var uvi: Double? = null,
    @SerializedName("visibility")
    var visibility: Int? = null,
    @SerializedName("weather")
    var weather: List<HistoricalWeatherWeather>? = null,
    @SerializedName("wind_deg")
    var windDeg: Int? = null,
    @SerializedName("wind_speed")
    var windSpeed: Double? = null
)

@Keep
data class HistoricalWeatherRain(
    @SerializedName("1h")
    var h: Double? = null
)

@Keep
data class HistoricalWeatherSnow(
    @SerializedName("1h")
    var h: Double? = null
)

@Keep
data class HistoricalWeatherWeather(
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("main")
    var main: String? = null
)