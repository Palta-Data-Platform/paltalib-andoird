package com.paltabrain.billing

import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.data.Purchase

interface PurchasesUpdatedListener {

    fun onPurchasesUpdated(result: BillingResult, purchases: List<Purchase>?)
}
