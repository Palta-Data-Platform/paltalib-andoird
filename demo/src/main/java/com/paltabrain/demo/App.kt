package com.paltabrain.demo

import android.app.Application
import com.paltabrain.PaltaAnalytics

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        PaltaAnalytics.instance.initialize(applicationContext, "yourApiKey")
    }

}