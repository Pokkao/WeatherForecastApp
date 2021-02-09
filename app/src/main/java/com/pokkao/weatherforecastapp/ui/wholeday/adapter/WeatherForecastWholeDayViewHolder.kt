package com.pokkao.weatherforecastapp.ui.wholeday.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokkao.weatherforecastapp.R
import com.pokkao.weatherforecastapp.data.model.WeatherForecastTodayItem
import com.pokkao.weatherforecastapp.utils.*
import kotlinx.android.synthetic.main.item_weather_forecast_whole_day.view.*

class WeatherForecastWholeDayViewHolder(private val containerView: View) : RecyclerView.ViewHolder(
    containerView
) {

    fun render(data: WeatherForecastTodayItem?) {
        data?.let {
            itemView.tvTime.text =
                it.dt?.let { it1 ->
                    convert24HourTo12Hour(
                        convertTimestampToDate(it1, DATE_TIME_PATTERN)
                    )
                }

            val temp = it.temp.toString().split(".")
            itemView.tvCelsiusDegreeItem.text = if (temp[0] == "-0") {
                "${temp[0].replace("-", "")}°/"
            } else {
                "${temp[0]}°/" ?: "-"
            }

            itemView.tvFahrenheitDegreeItem.text =
                "${convertCelsiusDegreeToFahrenheit(temp[0]).split(".")[0]}°"

            itemView.tvHumidityItem.text =
                "${it.humidity ?: "0"}${(Contextor.context.getString(R.string.percent))}"
        }
    }

    companion object {

        @JvmStatic
        fun create(parent: ViewGroup): WeatherForecastWholeDayViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_weather_forecast_whole_day,
                parent,
                false
            )
            return WeatherForecastWholeDayViewHolder(view)
        }
    }
}
