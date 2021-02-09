package com.pokkao.weatherforecastapp.di.module

import com.pokkao.weatherforecastapp.ui.main.WeatherForecastContract
import com.pokkao.weatherforecastapp.ui.main.WeatherForecastMainFragment
import com.pokkao.weatherforecastapp.ui.main.WeatherForecastPresenter
import com.pokkao.weatherforecastapp.ui.wholeday.WeatherForecastWholeDayContract
import com.pokkao.weatherforecastapp.ui.wholeday.WeatherForecastWholeDayFragment
import com.pokkao.weatherforecastapp.ui.wholeday.WeatherForecastWholeDayPresenter
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun provideWeatherForecastMainFragment(): WeatherForecastMainFragment

    @ContributesAndroidInjector
    abstract fun provideWeatherForecastWholeDayFragment(): WeatherForecastWholeDayFragment

    @ContributesAndroidInjector
    abstract fun provideWeatherForecastPresenter(): WeatherForecastPresenter

    @ContributesAndroidInjector
    abstract fun provideWeatherForecastWholeDayPresenter(): WeatherForecastWholeDayPresenter

    @ContributesAndroidInjector
    abstract fun provideWeatherForecastContract(): WeatherForecastContract.Presenter

    @ContributesAndroidInjector
    abstract fun provideWholeDayContract(): WeatherForecastWholeDayContract.Presenter

    @ContributesAndroidInjector
    abstract fun provideWeatherForecastContractView(): WeatherForecastContract.View

    @ContributesAndroidInjector
    abstract fun provideWholeDayContractView(): WeatherForecastWholeDayContract.View

}
