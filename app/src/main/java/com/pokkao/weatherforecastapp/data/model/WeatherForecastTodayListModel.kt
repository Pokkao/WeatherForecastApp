package com.pokkao.weatherforecastapp.data.model

class WeatherForecastTodayListModel (
    var weatherForecastToday: List<WeatherForecastTodayItem> ?= null,
)

data class WeatherForecastTodayItem(
    var dt: Long? = null,
    var temp: Double? = null,
    var humidity: Int? = null,
    var idWeather: Int? = null,
    var weather: String? = null,
    var weatherDescription: String? = null
)
