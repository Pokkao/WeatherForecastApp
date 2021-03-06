package com.pokkao.weatherforecastapp.data.repository

import com.pokkao.weatherforecastapp.data.response.HourlyForecastFortyEightHoursResponse
import com.pokkao.weatherforecastapp.data.response.WeatherTodayResponse
import com.pokkao.weatherforecastapp.data.service.WeatherService
import io.reactivex.Observable
import javax.inject.Inject

interface WeatherForecastWholeDayRepository {
    fun getWeatherTodayService(
        lat: String,
        lon: String,
        dt: String
    ): Observable<WeatherTodayResponse>

    fun getHourlyForecastFor48HoursService(
        lat: String,
        lon: String
    ): Observable<HourlyForecastFortyEightHoursResponse>
}

class WeatherForecastWholeDayRepositoryImpl @Inject constructor(private val service: WeatherService) :
    WeatherForecastWholeDayRepository {

    override fun getWeatherTodayService(
        lat: String,
        lon: String,
        dt: String
    ): Observable<WeatherTodayResponse> {
        return service.getWeatherTodayService(
            lat,
            lon,
            dt
        )
    }

    override fun getHourlyForecastFor48HoursService(
        lat: String,
        lon: String
    ): Observable<HourlyForecastFortyEightHoursResponse> {
        return service.getHourlyForecastFor48HoursService(
            lat,
            lon
        )
    }

}