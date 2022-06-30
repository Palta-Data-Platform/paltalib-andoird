package com.paltabrain.purchases.demo

import android.app.Application
import android.util.Log
import com.paltabrain.billing.BillingClientStateListener
import com.paltabrain.billing.PaltaBilling
import com.paltabrain.billing.PurchasesUpdatedListener
import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.data.Purchase

class App : Application(),
    BillingClientStateListener,
    PurchasesUpdatedListener {

    private lateinit var client: PaltaBilling

    override fun onCreate() {
        super.onCreate()
        client = PaltaBilling(applicationContext, this)
        client.startConnection(this)
    }

    override fun onBillingSetupFinished(result: BillingResult) {
        Log.wtf("!@!", "onBillingSetupFinished:$result")
    }

    override fun onBillingServiceDisconnected() {
        Log.wtf("!@!", "onBillingServiceDisconnected")
    }

    override fun onPurchasesUpdated(result: BillingResult, purchases: List<Purchase>?) {
        Log.wtf("!@!", "onPurchasesUpdated:$result:$purchases")
    }
}
