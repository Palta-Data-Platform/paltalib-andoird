package com.paltabrain.billing

import android.content.Context

class PaltaBilling(
    context: Context,
    purchasesUpdatedListener: PurchasesUpdatedListener,
) : BillingClient {

    private val androidBillingWrapper = AndroidBillingWrapper(context, purchasesUpdatedListener)

    override fun startConnection(callback: BillingClientStateListener) {
        androidBillingWrapper.startConnection(callback)
    }

    override fun endConnection() {
        androidBillingWrapper.endConnection()
    }
}