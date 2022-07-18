package com.paltabrain.billing.mappers

import com.paltabrain.billing.data.ProductDetails

interface BillingProductDetailMapper<T> {

    fun map(productDetails: T): ProductDetails
}
