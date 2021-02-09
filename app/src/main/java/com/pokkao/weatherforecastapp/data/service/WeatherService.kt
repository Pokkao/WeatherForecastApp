package com.pokkao.weatherforecastapp.data.service

import com.pokkao.weatherforecastapp.data.response.CurrentWeatherResponse
import com.pokkao.weatherforecastapp.data.response.HourlyForecastFortyEightHoursResponse
import com.pokkao.weatherforecastapp.data.response.WeatherTodayResponse
import com.pokkao.weatherforecastapp.data.ApiClient.API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    companion object {
        private const val PATH_WEATHER = "/data/2.5"
    }

    @GET("${PATH_WEATHER}/weather")
    fun getCurrentWeatherService(@Query("q") location: String,
                             @Query("units") units: String = "metric",
                             @Query("appid") appid: String = API_KEY
    ): Single<CurrentWeatherResponse>

    @GET("${PATH_WEATHER}/onecall/timemachine")
    fun getWeatherTodayService(@Query("lat") lat: String ?= null,
                               @Query("lon") lon: String ?= null,
                               @Query("dt") dt: String ?= null,
                               @Query("units") units: String = "metric",
                               @Query("appid") appid: String = API_KEY
    ): Single<WeatherTodayResponse>

    @GET("${PATH_WEATHER}/onecall")
    fun getHourlyForecastFor48HoursService(@Query("lat") lat: String,
                                    @Query("lon") lon: String,
                                    @Query("exclude") cnt: String = "current,minutely,daily,alerts",
                                    @Query("units") units: String = "metric",
                                    @Query("appid") appid: String = API_KEY
    ): Single<HourlyForecastFortyEightHoursResponse>

}
