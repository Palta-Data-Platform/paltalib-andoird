package com.paltabrain.billing.mappers

import com.paltabrain.billing.data.BillingResponseCode
import com.paltabrain.billing.data.BillingResult
import com.android.billingclient.api.BillingResult as AndroidBillingResult

class AndroidBillingResultMapper : BillingResultMapper<AndroidBillingResult> {

    override fun map(result: AndroidBillingResult): BillingResult {
        val responseCore = when (result.responseCode) {
            -3 -> BillingResponseCode.SERVICE_TIMEOUT
            -2 -> BillingResponseCode.FEATURE_NOT_SUPPORTED
            -1 -> BillingResponseCode.SERVICE_DISCONNECTED
            0 -> BillingResponseCode.OK
            1 -> BillingResponseCode.USER_CANCELED
            2 -> BillingResponseCode.SERVICE_UNAVAILABLE
            3 -> BillingResponseCode.BILLING_UNAVAILABLE
            4 -> BillingResponseCode.ITEM_UNAVAILABLE
            5 -> BillingResponseCode.DEVELOPER_ERROR
            6 -> BillingResponseCode.ERROR
            7 -> BillingResponseCode.ITEM_ALREADY_OWNED
            8 -> BillingResponseCode.ITEM_NOT_OWNED
            else -> BillingResponseCode.UNKNOWN
        }
        return BillingResult(responseCore, result.debugMessage)
    }
}