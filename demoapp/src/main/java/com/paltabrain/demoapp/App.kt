package com.paltabrain.demoapp

import android.app.Application
import com.paltabrain.PaltaAnalytics
import com.paltabrain.purchases.PaltaPurchases

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Paltabrain api key
        PaltaAnalytics.instance.initialize(applicationContext, "YOUR_ANALYTICS_API_KEY")

        val attributionInstance = com.paltabrain.attribution.PaltaAttribution.instance
        attributionInstance.configure(this,
            "YOUR_APPSFLYER_KEY", BuildConfig.ONELINK_ID)

        PaltaPurchases.instance
            .configure(this,
                "YOUR_REVENUECAT_API_KEY",
                "USER_ID",
                true,
                "WEB_SUBSCRIPTION_ID"
            )

    }
}
