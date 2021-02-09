package com.pokkao.weatherforecastapp.ui.wholeday

import com.pokkao.weatherforecastapp.data.model.WeatherForecastTodayListModel
import com.pokkao.weatherforecastapp.ui.base.BaseContract

interface WeatherForecastWholeDayContract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        // WeatherForecastWholeDay
        fun setViewWeatherForecastWholeDay(data: WeatherForecastTodayListModel)

        fun onWeatherForecastWholeDayFailure(throwable: Throwable?)

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getWeatherToday(
            lat: String,
            lon: String,
            dt: String
        )
    }

}
