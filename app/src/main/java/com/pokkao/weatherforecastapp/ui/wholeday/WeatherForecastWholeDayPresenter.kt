package com.pokkao.weatherforecastapp.ui.wholeday

import android.annotation.SuppressLint
import com.pokkao.weatherforecastapp.data.mapper.WeatherMapper
import com.pokkao.weatherforecastapp.data.model.WeatherForecastTodayItem
import com.pokkao.weatherforecastapp.data.model.WeatherForecastTodayListModel
import com.pokkao.weatherforecastapp.data.repository.WeatherForecastWholeDayRepository
import com.pokkao.weatherforecastapp.data.response.HourlyForecastFortyEightHoursResponse
import com.pokkao.weatherforecastapp.data.response.WeatherTodayResponse
import com.pokkao.weatherforecastapp.utils.convertTimestampToDate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherForecastWholeDayPresenter @Inject constructor(
    var view: WeatherForecastWholeDayContract.View,
    private var repository: WeatherForecastWholeDayRepository
) : WeatherForecastWholeDayContract.Presenter {

    override fun attach(view: WeatherForecastWholeDayContract.View) {
        this.view = view
    }

    var weatherForecastTodayList = mutableListOf<WeatherForecastTodayItem>()

    @SuppressLint("CheckResult")
    override fun getWeatherToday(
        lat: String,
        lon: String,
        dt: String
    ) {
        view.showProgress()

        Observable.zip(
            repository.getWeatherTodayService(
                lat,
                lon,
                dt
            ),
            repository.getHourlyForecastFor48HoursService(
                lat,
                lon
            ), { weatherToday: WeatherTodayResponse?, hourlyForecast: HourlyForecastFortyEightHoursResponse? ->

                WeatherMapper().mapWeatherToday(weatherToday?.hourly)?.toList()?.let {
                    weatherForecastTodayList.addAll(it)
                } ?: run {
                    weatherForecastTodayList.addAll(listOf())
                }

                val currentTime = System.currentTimeMillis() / 1000

                // Map model
                val mapHistoryWeather = hourlyForecast?.hourly?.filter {
                    convertTimestampToDate(currentTime) == convertTimestampToDate(
                        it.dt ?: 0
                    )
                }

                // Add all list with Current Weather
                WeatherMapper().mapHistoryWeather(mapHistoryWeather)?.toList()?.let {
                    weatherForecastTodayList.addAll(it)
                } ?: run {
                    weatherForecastTodayList.addAll(listOf())
                }

                // Filter specific today
                WeatherForecastTodayListModel(
                    weatherForecastToday = weatherForecastTodayList.filter {
                        convertTimestampToDate(currentTime) == convertTimestampToDate(
                            it.dt ?: 0
                        )
                    }
                )

            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    view.setViewWeatherForecastWholeDay(data)
                    view.hideProgress()
                }, { error ->
                    view.onWeatherForecastWholeDayFailure(error)
                    view.hideProgress()
                }
            )
    }

}
