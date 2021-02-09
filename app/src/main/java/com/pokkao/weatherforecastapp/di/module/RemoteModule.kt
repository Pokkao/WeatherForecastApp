package com.pokkao.weatherforecastapp.di.module

import com.pokkao.weatherforecastapp.data.service.WeatherService
import com.pokkao.weatherforecastapp.data.ApiClient.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideWeatherForecastService(client: OkHttpClient.Builder): WeatherService {
        val retrofit = createBaseRetrofitBuilder(client.build(), BASE_URL)
        return retrofit.build().create(WeatherService::class.java)
    }

    @Provides
    fun createBaseOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
    }

    private fun createBaseRetrofitBuilder(okHttpClient: OkHttpClient, baseUrl: String): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    }

}
