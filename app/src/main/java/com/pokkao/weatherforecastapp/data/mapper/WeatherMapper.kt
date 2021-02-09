package com.pokkao.weatherforecastapp.data.mapper

import com.pokkao.weatherforecastapp.data.model.WeatherForecastTodayItem
import com.pokkao.weatherforecastapp.data.response.HistoricalWeatherHourly
import com.pokkao.weatherforecastapp.data.response.WeatherTodayHourly

class WeatherMapper {

    fun mapWeatherToday(hourlyList: List<WeatherTodayHourly>?): List<WeatherForecastTodayItem>? {
        return hourlyList?.mapIndexed { _, data ->
            WeatherForecastTodayItem(
                dt = data.dt,
                temp = data.temp,
                humidity = data.humidity,
                idWeather = data.weather?.get(0)?.id ?: 0,
                weather = data.weather?.get(0)?.main,
                weatherDescription = data.weather?.get(0)?.description
            )
        }
    }

    fun mapHistoryWeather(mapHistoryWeather: List<HistoricalWeatherHourly>?): List<WeatherForecastTodayItem>? {
        return mapHistoryWeather?.mapIndexed { _, data ->
            WeatherForecastTodayItem(
                dt = data.dt,
                temp = data.temp,
                humidity = data.humidity,
                idWeather = data.weather?.get(0)?.id ?: 0,
                weather = data.weather?.get(0)?.main,
                weatherDescription = data.weather?.get(0)?.description
            )
        }
    }

}
