package com.home.retrofitwebservicedemo2.common.di.other

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.home.retrofitwebservicedemo2.common.RWSDApplication
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector

object ApplicationInjector {

    fun init(application: RWSDApplication) {
        application.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) {}

                override fun onActivityResumed(activity: Activity) {}

                override fun onActivityPaused(activity: Activity) {}

                override fun onActivityStopped(activity: Activity) {}

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

                override fun onActivityDestroyed(activity: Activity) {}
            })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasAndroidInjector) AndroidInjection.inject(activity)
    }
}
