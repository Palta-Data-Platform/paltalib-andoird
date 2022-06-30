package com.paltabrain.billing.mappers

import com.android.billingclient.api.ProductDetails as AndroidProductDetails
import com.paltabrain.billing.data.ProductDetails

class BillingProductDetailMapper {

    fun map(productDetail: AndroidProductDetails): ProductDetails {
        return ProductDetails(
            id = productDetail.productId,
            productId = productDetail.productId,
            productType = productDetail.productType,
            title = productDetail.title,
            name = productDetail.name,
            description = productDetail.description,
        )
    }
}