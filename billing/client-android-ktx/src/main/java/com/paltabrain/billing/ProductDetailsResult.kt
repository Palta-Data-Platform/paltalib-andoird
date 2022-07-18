package com.paltabrain.billing

import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.data.ProductDetails

data class ProductDetailsResult(
    val result: BillingResult,
    val productDetails: List<ProductDetails>,
)
