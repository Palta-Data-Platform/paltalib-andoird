package com.paltabrain.purchases.demo

import android.app.Application
import android.util.Log
import com.paltabrain.billing.*
import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.data.Purchase
import com.paltabrain.billing.interfaces.Billing
import com.paltabrain.billing.rc.PurchasesConfiguration
import com.paltabrain.billing.rc.RCBilling
import com.paltabrain.core.logger.Logger
import com.paltabrain.core.logger.android.AndroidLogger

class App : Application(),
    PurchasesUpdatedListener {

    val logger: Logger = AndroidLogger()
    lateinit var client: Billing

    override fun onCreate() {
        super.onCreate()
        val wrapper = RCBilling()
        client = wrapper
        wrapper.config(
            PurchasesConfiguration(
                this,
                apiKey = "goog_IhMfJHEnxAblynXpnQGiXPioENb",
                appUserId = "",
                observerMode = false,
                debugLogsEnabled = BuildConfig.DEBUG,
            )
        )
    }

    override fun onPurchasesUpdated(result: BillingResult, purchases: List<Purchase>?) {
        Log.wtf("!@!", "onPurchasesUpdated:$result:$purchases")
    }
}
