package com.pokkao.weatherforecastapp.ui.main

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pokkao.weatherforecastapp.R
import com.pokkao.weatherforecastapp.data.model.LocationForWeatherForecastWholeDayModel
import com.pokkao.weatherforecastapp.data.repository.CurrentWeatherRepository
import com.pokkao.weatherforecastapp.data.response.CurrentWeatherResponse
import com.pokkao.weatherforecastapp.extensions.gone
import com.pokkao.weatherforecastapp.extensions.invisible
import com.pokkao.weatherforecastapp.extensions.setImage
import com.pokkao.weatherforecastapp.extensions.visible
import com.pokkao.weatherforecastapp.ui.HomeMainActivity
import com.pokkao.weatherforecastapp.ui.wholeday.WeatherForecastWholeDayFragment
import com.pokkao.weatherforecastapp.utils.convertCelsiusDegreeToFahrenheit
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_weather_forecast_main.*
import javax.inject.Inject


class WeatherForecastMainFragment : DaggerFragment(), WeatherForecastContract.View {

    @Inject
    lateinit var repository: CurrentWeatherRepository

    private var dataCurrentWeather: CurrentWeatherResponse? = null
    private var fahrebheit = ""
    private var location = ""

    private val mPresenter: WeatherForecastContract.Presenter by lazy {
        WeatherForecastPresenter(this, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_forecast_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeMainActivity).setupClickOutsideKeyboard(view.findViewById(R.id.contWeatherForecast))
        showHideProgressDialog(true)

        // Default "Bongkok" when entry first
        mPresenter.getCurrentWeather("Bangkok")

        setListener()

    }

    private fun setListener() {

        tvCelsiusDegree.setOnClickListener {
            dataCurrentWeather?.let {
                val temp = it.main?.temp.toString().split(".")
                tvDegree.text = if (temp[0] == "-0") {
                    "${temp[0].replace("-", "")}"
                } else {
                    "${temp[0]}" ?: "-"
                }
            }

            tvCelsiusDegree.setTypeface(tvCelsiusDegree.typeface, Typeface.BOLD)
            tvFahrenheitDegree.setTypeface(null, Typeface.NORMAL)
        }

        tvFahrenheitDegree.setOnClickListener {
            tvDegree.text = fahrebheit

            tvCelsiusDegree.setTypeface(null, Typeface.NORMAL)
            tvFahrenheitDegree.setTypeface(tvFahrenheitDegree.typeface, Typeface.BOLD)
        }

        bntConfirmWeatherForecast.setOnClickListener {
            if (edCityName.getText().isEmpty()) {
                edCityName.showError()
            } else {
                edCityName.hideError()
                location = when {
                    edStateCode.getText().isNotEmpty() &&
                            edCountryCode.getText().isNotEmpty() -> {
                        edCityName.getText()
                            .plus(",${edStateCode.getText()},${edCountryCode.getText()}")
                    }
                    edStateCode.getText().isNotEmpty() -> {
                        edCityName.getText().plus(",${edStateCode.getText()}")
                    }
                    edCountryCode.getText().isNotEmpty() -> {
                        edCityName.getText().plus(",${edCountryCode.getText()}")
                    }
                    else -> edCityName.getText()
                }

                mPresenter.getCurrentWeather(location)
            }

        }

        bntForecastWeatherAllDay.setOnClickListener {
            mPresenter.mapLocationForWeatherForecastWholeDayModel(dataCurrentWeather)
        }

        with(swipeRefreshWeatherForecast) {
            setColorSchemeResources(R.color.blue_fresh)
            setOnRefreshListener {
                mPresenter.getCurrentWeather(
                    if (tvLocationName.text == getString(R.string.not_found_place)) {
                        location
                    } else {
                        tvLocationName.text.toString()
                    }
                )
                swipeRefreshWeatherForecast.isRefreshing = false
            }
        }

    }

    private fun setView(data: CurrentWeatherResponse?) {
        dataCurrentWeather = data
        if (data != null) {
            tvCelsiusDegree.setTypeface(tvCelsiusDegree.typeface, Typeface.BOLD)
            tvFahrenheitDegree.setTypeface(null, Typeface.NORMAL)

            data.weather?.get(0)?.main?.let {
                mPresenter.mapIconWeather(it)
            }
            tvWeatherDescription.text = data.weather?.get(0)?.description ?: ""

            val temp = data.main?.temp.toString().split(".")
            tvDegree.text = if (temp[0] == "-0") {
                "${temp[0].replace("-", "")}"
            } else {
                "${temp[0]}" ?: "-"
            }

            fahrebheit = convertCelsiusDegreeToFahrenheit(temp[0]).split(".")[0]

            tvLocationName.text = data.name
            tvHumidity.text =
                "${getString(R.string.forecast_humidity)} ${data.main?.humidity.toString()} %"
        } else {
            fahrebheit = getString(R.string.txt_not_data)
            tvLocationName.text = getString(R.string.not_found_place)
            tvDegree.text = getString(R.string.txt_not_data)
            tvWeatherDescription.text = getString(R.string.can_not_identified)
            tvHumidity.text = "${getString(R.string.forecast_humidity)} - %"

        }
    }

    private fun scrollToTop() {
        nestWeatherForecastMain.apply {
            fullScroll(View.FOCUS_UP)
            smoothScrollTo(0, 0)
        }
    }

    override fun showProgress() {
        showHideProgressDialog(true)
    }

    override fun hideProgress() {
        showHideProgressDialog(false)
    }

    override fun setDataCurrentWeather(data: CurrentWeatherResponse?) {
        if (data != null) {
            scrollToTop()
            tvWeatherDescription.visible()
            setView(data)
        } else {
            tvWeatherDescription.invisible()
            setView(data)
            alertDialogFailed(getString(R.string.txt_error_no_data_search_try_again))
        }
    }

    override fun onCurrentWeatherFailure(throwable: Throwable?) {
        tvWeatherDescription.invisible()
        alertDialogFailed(getString(R.string.txt_error_failed_try_again))
        setView(null)
    }

    override fun mapLocationForWeatherForecast(model: LocationForWeatherForecastWholeDayModel) {
        (activity as HomeMainActivity).addFragmentWithBackStackContentContainer(
            WeatherForecastWholeDayFragment.newInstance(model),
            WeatherForecastWholeDayFragment.TAG_NAME,
            WeatherForecastWholeDayFragment.TAG_NAME
        )
    }

    override fun setImageWeather(weatherInt: Int) {
        imgWeather.setImage(weatherInt)
    }


    private fun showHideProgressDialog(enable: Boolean) {
        if (enable) {
            progressBarWeather.visible()
        } else {
            progressBarWeather.gone()
        }
    }

    private fun alertDialogFailed(message: String) {
        AlertDialog.Builder(context).apply {
            setTitle(getString(R.string.txt_something_went_wrong))
            setMessage(message)
            setCancelable(true)
            setPositiveButton(
                getString(R.string.ok)
            ) { dialog, _ ->
                dialog.cancel()
            }
        }.create().show()
    }

    companion object {
        const val TAG_NAME = "WeatherForecastMainFragment"
        fun newInstance() = WeatherForecastMainFragment()
    }

}
