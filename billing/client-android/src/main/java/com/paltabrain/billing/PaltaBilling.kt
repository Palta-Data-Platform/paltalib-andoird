package com.paltabrain.billing

import android.content.Context
import com.paltabrain.billing.data.ProductDetails

class PaltaBilling(
    context: Context,
    purchasesUpdatedListener: PurchasesUpdatedListener,
    localStorage: LocalStorage,
)  {

    //    private val androidBillingWrapper = AndroidBillingWrapper(context, purchasesUpdatedListener, localStorage)
//
//    override val isReady: Boolean
//        get() = androidBillingWrapper.isReady
//
//    override fun startConnection(callback: BillingClientStateListener) {
//        androidBillingWrapper.startConnection(callback)
//    }
//
//    override fun endConnection() {
//        androidBillingWrapper.endConnection()
//    }
//
//    override fun queryProductDetails(products: List<Product>, callback: ProductDetailsResponseListener) {
//        androidBillingWrapper.queryProductDetails(products, callback)
//    }
//
//    override fun buy(billingContext: BillingContext, productDetails: ProductDetails) {
//        androidBillingWrapper.buy(billingContext, productDetails)
//    }

}