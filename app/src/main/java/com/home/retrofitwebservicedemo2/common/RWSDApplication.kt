package com.home.retrofitwebservicedemo2.common

import android.app.Application
import com.home.retrofitwebservicedemo2.common.di.component.ApplicationComponent
import com.home.retrofitwebservicedemo2.common.di.component.DaggerApplicationComponent
import com.home.retrofitwebservicedemo2.common.di.other.ApplicationInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RWSDApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        ApplicationInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}
