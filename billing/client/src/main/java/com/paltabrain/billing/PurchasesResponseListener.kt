package com.paltabrain.billing

import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.data.Purchase

interface PurchasesResponseListener {

    fun onQueryPurchasesResponse(result: BillingResult, purchases: List<Purchase>)
}
