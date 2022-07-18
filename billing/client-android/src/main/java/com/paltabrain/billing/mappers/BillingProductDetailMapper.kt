package com.paltabrain.billing.mappers

import com.paltabrain.billing.data.ProductDetails
import com.paltabrain.billing.data.PurchaseOfferDetails
import com.android.billingclient.api.SkuDetails as AndroidProductDetails

class AndroidBillingProductDetailMapper : BillingProductDetailMapper<AndroidProductDetails> {

    override fun map(productDetails: AndroidProductDetails): ProductDetails {
        return if (isInAppProduct(productDetails)) {
            mapInApp(productDetails)
        } else {
            mapSubscription(productDetails)
        }
    }

    private fun isInAppProduct(productDetails: AndroidProductDetails): Boolean {
        return productDetails.type == "INAPP" //??
    }

    private fun mapInApp(productDetails: AndroidProductDetails): ProductDetails.InApp {
        val purchaseOfferDetails = PurchaseOfferDetails.InAppOfferDetails(
            formattedPrice = productDetails.introductoryPrice,
            priceAmountMicros = productDetails.priceAmountMicros,
            priceCurrencyCode = productDetails.priceCurrencyCode,
        )
        val originalJson = productDetails.originalJson
        return ProductDetails.InApp(
            id = productDetails.sku,
            productId = productDetails.sku,
            productType = productDetails.type,
            title = productDetails.title,
            name = productDetails.title,
            description = productDetails.description,
            purchaseOfferDetails = purchaseOfferDetails,
            originalJson = originalJson,
        )
    }

    private fun mapSubscription(productDetails: AndroidProductDetails): ProductDetails.Subscription {
//        val purchaseOfferDetails: List<PurchaseOfferDetails.SubOfferDetails> = productDetails.subscriptionPeriod.map {
//            PurchaseOfferDetails.SubOfferDetails(
//                offerToken = it.offerToken,
//                offerTags = it.offerTags
//            )
//        }
        val originalJson = productDetails.originalJson
        return ProductDetails.Subscription(
            id = productDetails.sku,
            productId = productDetails.sku,
            productType = productDetails.type,
            title = productDetails.title,
            name = productDetails.title,
            description = productDetails.description,
            purchaseOfferDetails = emptyList(),
            originalJson = originalJson,
        )
    }
}
