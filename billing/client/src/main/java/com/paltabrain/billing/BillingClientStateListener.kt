package com.paltabrain.billing

import com.paltabrain.billing.data.BillingResult

interface BillingClientStateListener {

    fun onBillingServiceDisconnected()
    fun onBillingSetupFinished(result: BillingResult)
}
