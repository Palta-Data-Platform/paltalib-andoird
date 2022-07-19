package com.paltabrain.billing.data

sealed class ProductDetails {
    data class InApp(
        val id: String,
        val productId: String,
        val productType: String,
        val title: String,
        val name: String,
        val description: String,
        val purchaseOfferDetails: PurchaseOfferDetails.InAppOfferDetails,
        val originalJson: String,
    ) : ProductDetails()

    data class Subscription(
        val id: String,
        val productId: String,
        val productType: String,
        val title: String,
        val name: String,
        val description: String,
        val purchaseOfferDetails: List<PurchaseOfferDetails.SubOfferDetails>,
        val originalJson: String,
    ) : ProductDetails()
}
