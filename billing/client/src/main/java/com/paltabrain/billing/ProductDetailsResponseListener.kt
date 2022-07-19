package com.paltabrain.billing

import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.data.ProductDetails

interface ProductDetailsResponseListener {

    fun onProductDetailsResponse(result: BillingResult, productDetails: List<ProductDetails>)
}
