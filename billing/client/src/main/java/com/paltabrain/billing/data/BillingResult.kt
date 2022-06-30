package com.paltabrain.billing.data

data class BillingResult(
    val responseCode: BillingResponseCode,
    val message: String,
)
