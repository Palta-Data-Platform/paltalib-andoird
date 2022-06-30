package com.paltabrain.billing.mappers


import com.paltabrain.billing.data.Purchase
import com.android.billingclient.api.Purchase as AndroidPurchase

class PurchaseMapper {

    fun map(purchase: AndroidPurchase): Purchase {
        return Purchase(
            purchaseState = purchase.purchaseState,
            quantity = purchase.quantity,
            purchaseTime = purchase.purchaseTime,
            orderId = purchase.orderId,
            originalJson = purchase.originalJson,
            purchaseToken = purchase.purchaseToken,
            signature = purchase.signature,
        )
    }
}