package com.pokkao.weatherforecastapp

import android.app.Application
import com.pokkao.weatherforecastapp.di.component.DaggerAppComponent
import com.pokkao.weatherforecastapp.utils.Contextor
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        daggerBuilder()
        Contextor.context = this
    }

    private fun daggerBuilder() {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

}
