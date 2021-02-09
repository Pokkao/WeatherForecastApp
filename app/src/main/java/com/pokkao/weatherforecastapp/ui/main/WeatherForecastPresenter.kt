package com.pokkao.weatherforecastapp.ui.main

import android.annotation.SuppressLint
import com.pokkao.weatherforecastapp.R
import com.pokkao.weatherforecastapp.data.model.LocationForWeatherForecastWholeDayModel
import com.pokkao.weatherforecastapp.data.repository.CurrentWeatherRepository
import com.pokkao.weatherforecastapp.data.response.CurrentWeatherResponse
import com.pokkao.weatherforecastapp.ui.WeatherConfig.WEATHER_CLEAR
import com.pokkao.weatherforecastapp.ui.WeatherConfig.WEATHER_CLOUDS
import com.pokkao.weatherforecastapp.ui.WeatherConfig.WEATHER_DRIZZLE
import com.pokkao.weatherforecastapp.ui.WeatherConfig.WEATHER_RAIN
import com.pokkao.weatherforecastapp.ui.WeatherConfig.WEATHER_SNOW
import com.pokkao.weatherforecastapp.ui.WeatherConfig.WEATHER_THUNDERSTORM
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherForecastPresenter @Inject constructor (
    var view: WeatherForecastContract.View,
    private var repository: CurrentWeatherRepository
) : WeatherForecastContract.Presenter {

    override fun attach(view: WeatherForecastContract.View) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    override fun getCurrentWeather(location: String) {
        view.showProgress()

        repository.getCurrentWeatherService(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    view.hideProgress()
                    view.setDataCurrentWeather(data)
                }, { error ->
                    view.hideProgress()
                    view.onCurrentWeatherFailure(error)
                }
            )
    }

    override fun mapLocationForWeatherForecastWholeDayModel(dataCurrentWeather: CurrentWeatherResponse?) {
        view.mapLocationForWeatherForecast(
            LocationForWeatherForecastWholeDayModel(
                latitude = dataCurrentWeather?.coord?.lat.toString(),
                longtitude = dataCurrentWeather?.coord?.lon.toString(),
                timestamp = dataCurrentWeather?.dt.toString()
            )
        )
    }

    override fun mapIconWeather(mainWeather: String) {
        val imgWeather = when (mainWeather) {
            WEATHER_THUNDERSTORM -> {
                R.drawable.ic_thunderstorm
            }
            WEATHER_DRIZZLE -> {
                R.drawable.ic_drizzle
            }
            WEATHER_RAIN -> {
                R.drawable.ic_rain
            }
            WEATHER_SNOW -> {
                R.drawable.ic_snow
            }
            WEATHER_CLEAR -> {
                R.drawable.ic_clear
            }
            WEATHER_CLOUDS -> {
                R.drawable.ic_clouds
            }
            else -> {
                // Mist, Smoke, Haze, Dust, Fog, Sand, Ash, Squall, Tornado
                R.drawable.ic_mist
            }
        }

        view.setImageWeather(imgWeather)
    }

}
