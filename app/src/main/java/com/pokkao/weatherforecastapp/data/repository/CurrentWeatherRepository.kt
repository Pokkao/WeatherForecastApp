package com.pokkao.weatherforecastapp.data.repository

import com.pokkao.weatherforecastapp.data.response.CurrentWeatherResponse
import com.pokkao.weatherforecastapp.data.service.WeatherService
import io.reactivex.Single
import javax.inject.Inject

interface CurrentWeatherRepository {
    fun getCurrentWeatherService(location: String): Single<CurrentWeatherResponse>
}

class CurrentWeatherRepositoryImpl @Inject constructor(private val service: WeatherService) : CurrentWeatherRepository {
    override fun getCurrentWeatherService(location: String): Single<CurrentWeatherResponse> {
        return service.getCurrentWeatherService(location)
    }

}
