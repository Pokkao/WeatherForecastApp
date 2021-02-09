package com.pokkao.weatherforecastapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationForWeatherForecastWholeDayModel(
    var latitude: String = "",
    var longtitude: String = "",
    var timestamp: String = ""
) : Parcelable
