package com.pokkao.weatherforecastapp.di.component

import android.app.Application
import com.pokkao.weatherforecastapp.MainApplication
import com.pokkao.weatherforecastapp.di.module.RemoteModule
import com.pokkao.weatherforecastapp.di.module.RepositoryModule
import com.pokkao.weatherforecastapp.di.module.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RemoteModule::class,
        RepositoryModule::class,
        UiModule::class
    ]
)

interface AppComponent {
    fun inject(app: MainApplication)

    @Component.Builder
    interface Builder : ComponentBuilder<AppComponent> {
        @BindsInstance
        fun application(application: Application): Builder
    }

}

interface ComponentBuilder<out C> {
    fun build(): C
}
