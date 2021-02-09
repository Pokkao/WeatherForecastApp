package com.pokkao.weatherforecastapp.data.repository

import com.pokkao.weatherforecastapp.data.response.HourlyForecastFortyEightHoursResponse
import com.pokkao.weatherforecastapp.data.response.WeatherTodayResponse
import com.pokkao.weatherforecastapp.data.service.WeatherService
import io.reactivex.Single
import javax.inject.Inject

interface WeatherForecastWholeDayRepository {
    fun getWeatherTodayService(
        lat: String,
        lon: String,
        dt: String
    ): Single<WeatherTodayResponse>

    fun getHourlyForecastFor48HoursService(
        lat: String,
        lon: String
    ): Single<HourlyForecastFortyEightHoursResponse>
}

class WeatherForecastWholeDayRepositoryImpl @Inject constructor(private val service: WeatherService) :
    WeatherForecastWholeDayRepository {

    override fun getWeatherTodayService(
        lat: String,
        lon: String,
        dt: String
    ): Single<WeatherTodayResponse> {
        return service.getWeatherTodayService(
            lat,
            lon,
            dt
        )
    }

    override fun getHourlyForecastFor48HoursService(
        lat: String,
        lon: String
    ): Single<HourlyForecastFortyEightHoursResponse> {
        return service.getHourlyForecastFor48HoursService(
            lat,
            lon
        )
    }

}