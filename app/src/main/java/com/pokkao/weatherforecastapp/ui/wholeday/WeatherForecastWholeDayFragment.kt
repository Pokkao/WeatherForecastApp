package com.pokkao.weatherforecastapp.ui.wholeday

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pokkao.weatherforecastapp.R
import com.pokkao.weatherforecastapp.data.model.LocationForWeatherForecastWholeDayModel
import com.pokkao.weatherforecastapp.data.model.WeatherForecastTodayListModel
import com.pokkao.weatherforecastapp.data.repository.WeatherForecastWholeDayRepository
import com.pokkao.weatherforecastapp.extensions.gone
import com.pokkao.weatherforecastapp.extensions.visible
import com.pokkao.weatherforecastapp.ui.wholeday.adapter.WeatherForecastWholeDayAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_weather_forecast_whole_day.*
import javax.inject.Inject


class WeatherForecastWholeDayFragment : DaggerFragment(), WeatherForecastWholeDayContract.View {

    @Inject
    lateinit var repository: WeatherForecastWholeDayRepository

//    @Inject
//    lateinit var mPresenter: WeatherForecastWholeDayContract.Presenter

    private val mPresenter: WeatherForecastWholeDayContract.Presenter by lazy {
        WeatherForecastWholeDayPresenter(
            this,
            repository
        )
    }

    private var location: LocationForWeatherForecastWholeDayModel? = null

    private val mWeatherForecastWholeDayAdapter by lazy {
        WeatherForecastWholeDayAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            location = arguments?.getParcelable(EXTRA_LOCATION_WEATHER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_forecast_whole_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.getWeatherToday(
            location?.latitude ?: "",
            location?.longtitude ?: "",
            location?.timestamp ?: ""
        )

        setView()
    }


    private fun setView() {
        rvWeatherForecastWholeDay.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mWeatherForecastWholeDayAdapter
        }
    }


    override fun showProgress() {
        showHideProgressDialog(true)
    }

    override fun hideProgress() {
        showHideProgressDialog(false)
    }

    override fun setViewWeatherForecastWholeDay(data: WeatherForecastTodayListModel) {

        if (!data.weatherForecastToday.isNullOrEmpty()) {
            tvWeatherList.gone()
            mWeatherForecastWholeDayAdapter.addAllAndRefresh(data.weatherForecastToday)
        } else {
            tvWeatherList.visible()
            tvWeatherList.text = getString(R.string.txt_error_no_information_available)
        }
    }

    override fun onWeatherForecastWholeDayFailure(throwable: Throwable?) {
        alertDialogFailed()
        tvWeatherList.visible()
        tvWeatherList.text = getString(R.string.txt_error_failed)
    }

    private fun showHideProgressDialog(enable: Boolean) {
        if (enable) {
            progressBarWeatherWholeDay.visible()
        } else {
            progressBarWeatherWholeDay.gone()
        }
    }

    private fun alertDialogFailed() {
        AlertDialog.Builder(context).apply {
            setTitle(getString(R.string.txt_something_went_wrong))
            setMessage(getString(R.string.txt_please_try_again_on_whole_day))
            setCancelable(true)
            setPositiveButton(
                getString(R.string.ok)
            ) { dialog, _ ->
                dialog.cancel()
            }
        }.create().show()
    }

    companion object {
        const val TAG_NAME = "WeatherForecastWholeDayFragment"
        const val EXTRA_LOCATION_WEATHER = "EXTRA_LOCATION_WEATHER"

        @JvmStatic
        fun newInstance(location: LocationForWeatherForecastWholeDayModel) =
            WeatherForecastWholeDayFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_LOCATION_WEATHER, location)
                }
            }
    }

}
