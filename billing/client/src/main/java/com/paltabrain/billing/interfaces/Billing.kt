package com.paltabrain.billing.interfaces

import com.paltabrain.billing.BillingContext
import com.paltabrain.billing.purchases.Offerings
import com.paltabrain.billing.purchases.models.CustomerInfo
import com.paltabrain.billing.purchases.models.PurchasesError
import com.paltabrain.billing.purchases.models.StoreProduct

interface Billing {

    val appUserID: String

    var allowSharingPlayStoreAccount: Boolean

    fun restorePurchases(callback: ReceiveCustomerInfoCallback)

    fun getOfferingsWith(
        onError: (error: PurchasesError) -> Unit,
        onSuccess: (offerings: Offerings) -> Unit,
    )

    fun getPurchaserInfoWith(
        onError: (error: PurchasesError) -> Unit,
        onSuccess: (customerInfo: CustomerInfo) -> Unit,
    )

    fun purchaseProduct(
        context: BillingContext,
        storeProduct: StoreProduct,
        callback: PurchaseCallback,
    )
}
