package com.pokkao.weatherforecastapp.di.module

import com.pokkao.weatherforecastapp.data.repository.CurrentWeatherRepository
import com.pokkao.weatherforecastapp.data.repository.CurrentWeatherRepositoryImpl
import com.pokkao.weatherforecastapp.data.repository.WeatherForecastWholeDayRepository
import com.pokkao.weatherforecastapp.data.repository.WeatherForecastWholeDayRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideCurrentWeatherRepository(
        repositoryImpl: CurrentWeatherRepositoryImpl
    ): CurrentWeatherRepository

    @Singleton
    @Binds
    abstract fun provideWeatherForecastWholeDayRepository(
        repositoryImpl: WeatherForecastWholeDayRepositoryImpl
    ): WeatherForecastWholeDayRepository

}
