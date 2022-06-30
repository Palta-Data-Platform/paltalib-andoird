package com.paltabrain.billing

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import com.android.billingclient.api.PurchasesResponseListener
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.queryProductDetails
import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.data.Purchase
import com.paltabrain.billing.mappers.BillingResultMapper
import com.paltabrain.billing.mappers.PurchaseMapper
import com.android.billingclient.api.BillingClient as AndroidBillingClient
import com.android.billingclient.api.BillingClientStateListener as AndroidBillingClientStateListener
import com.android.billingclient.api.BillingResult as AndroidBillingResult
import com.android.billingclient.api.PurchasesUpdatedListener as AndroidPurchasesUpdatedListener

class AndroidBillingWrapper(
    context: Context,
    private val purchasesUpdatedListener: PurchasesUpdatedListener,
) : BillingClient {

    private val applicationContext = context.applicationContext

    private val purchaseMapper = PurchaseMapper()
    private val billingResultMapper = BillingResultMapper()
    private val purchasesUpdatedWrapper = AndroidPurchasesUpdatedListener { androidBillingResult, androidPurchases ->
        val billingResult = androidBillingResult.let(billingResultMapper::map)
        val purchases = androidPurchases?.map(purchaseMapper::map)
        purchasesUpdatedListener.onPurchasesUpdated(billingResult, purchases)
    }
    private val androidBillingClient: AndroidBillingClient = AndroidBillingClient.newBuilder(applicationContext)
        .setListener(purchasesUpdatedWrapper)
        .enablePendingPurchases() // Not used for subscriptions.
        .build()

    override fun startConnection(callback: BillingClientStateListener) {
        val wrapperCallback = object : AndroidBillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                callback.onBillingServiceDisconnected()
            }

            override fun onBillingSetupFinished(androidBillingResult: AndroidBillingResult) {
                val billingResult = androidBillingResult.let(billingResultMapper::map)
                callback.onBillingSetupFinished(billingResult)
            }
        }
        if (!androidBillingClient.isReady) {
            androidBillingClient.startConnection(wrapperCallback)
        }
    }

    override fun endConnection() {
        if (androidBillingClient.isReady) {
            androidBillingClient.endConnection()
        }
    }

    fun queryProductDetails() {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(AndroidBillingClient.ProductType.INAPP)
            .build()
        val listener = PurchasesResponseListener { p0, p1 -> Log.wtf("!@!", "onQueryPurchasesResponse:$p0:$p1") }
        androidBillingClient.queryPurchasesAsync(
            params,
            listener
        )
    }
}