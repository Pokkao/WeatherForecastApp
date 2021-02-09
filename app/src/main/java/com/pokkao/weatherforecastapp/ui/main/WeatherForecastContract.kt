package com.pokkao.weatherforecastapp.ui.main

import com.pokkao.weatherforecastapp.data.model.LocationForWeatherForecastWholeDayModel
import com.pokkao.weatherforecastapp.data.response.CurrentWeatherResponse
import com.pokkao.weatherforecastapp.ui.base.BaseContract

interface WeatherForecastContract {

    interface View: BaseContract.View {

        fun showProgress()

        fun hideProgress()

        // CurrentWeather
        fun setDataCurrentWeather(data: CurrentWeatherResponse?)

        fun onCurrentWeatherFailure(throwable: Throwable?)

        fun mapLocationForWeatherForecast(model: LocationForWeatherForecastWholeDayModel)

        fun setImageWeather(imgWeather: Int)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getCurrentWeather(location: String)

        fun mapLocationForWeatherForecastWholeDayModel(data: CurrentWeatherResponse?)

        fun mapIconWeather(mainWeather: String)
    }

}
