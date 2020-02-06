package com.home.retrofitwebservicedemo2.common.di.component

import android.app.Application
import com.home.retrofitwebservicedemo2.common.RWSDApplication
import com.home.retrofitwebservicedemo2.common.di.module.ActivityBindModule
import com.home.retrofitwebservicedemo2.common.di.module.AppModule
import com.home.retrofitwebservicedemo2.common.network.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class,
        ActivityBindModule::class, NetworkModule::class]
)
interface ApplicationComponent {

    fun inject(tFDApplication: RWSDApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
