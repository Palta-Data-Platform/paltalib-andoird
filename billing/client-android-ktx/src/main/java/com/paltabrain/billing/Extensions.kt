package com.paltabrain.billing

import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.data.ProductDetails
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

//suspend fun BillingClient.queryProductDetails(products: List<Product>): ProductDetailsResult = suspendCoroutine {
//
//    val callback = object : ProductDetailsResponseListener {
//        override fun onProductDetailsResponse(result: BillingResult, productDetails: List<ProductDetails>) {
//            it.resume(ProductDetailsResult(result, productDetails))
//        }
//    }
//    this.queryProductDetails(products, callback)
//}