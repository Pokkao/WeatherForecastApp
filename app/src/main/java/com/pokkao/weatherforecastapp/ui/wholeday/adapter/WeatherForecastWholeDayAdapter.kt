package com.pokkao.weatherforecastapp.ui.wholeday.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokkao.weatherforecastapp.data.model.WeatherForecastTodayItem

class WeatherForecastWholeDayAdapter : RecyclerView.Adapter<WeatherForecastWholeDayViewHolder>() {

    private val mWeatherForecastList by lazy {
        mutableListOf<WeatherForecastTodayItem>()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherForecastWholeDayViewHolder {
        return WeatherForecastWholeDayViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: WeatherForecastWholeDayViewHolder,
        position: Int
    ) {
        return holder.render(mWeatherForecastList[position])
    }

    override fun getItemCount(): Int {
        return mWeatherForecastList.size
    }

    fun addAllAndRefresh(listWeather: List<WeatherForecastTodayItem>?) {
        if (mWeatherForecastList.size > 0) mWeatherForecastList.clear()
        if (listWeather != null) {
            mWeatherForecastList.addAll(listWeather)
        }
        notifyDataSetChanged()
    }

}
