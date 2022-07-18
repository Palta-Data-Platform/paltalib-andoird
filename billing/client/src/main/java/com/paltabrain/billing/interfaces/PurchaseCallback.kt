package com.paltabrain.billing.interfaces

import com.paltabrain.billing.purchases.models.CustomerInfo
import com.paltabrain.billing.purchases.models.StoreTransaction

interface PurchaseCallback : PurchaseErrorCallback {
    /**
     * Will be called after the purchase has completed
     * @param storeTransaction StoreTransaction object for the purchased product.
     * @param customerInfo Updated [CustomerInfo].
     */
    fun onCompleted(storeTransaction: StoreTransaction, customerInfo: CustomerInfo)
}
