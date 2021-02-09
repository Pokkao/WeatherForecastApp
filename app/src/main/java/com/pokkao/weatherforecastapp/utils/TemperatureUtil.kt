package com.pokkao.weatherforecastapp.utils


fun convertCelsiusDegreeToFahrenheit(number: String): String {
    return try {
        ((number.toDouble() * 1.8) + 32).toString()
    } catch (e: Exception) {
        "-°"
    }
}