package com.paltabrain.billing.data

sealed class PurchaseOfferDetails {
    data class InAppOfferDetails(
        val formattedPrice: String,
        val priceAmountMicros: Long,
        val priceCurrencyCode: String,
    )

    data class SubOfferDetails(
        val offerToken: String,
        val offerTags: List<String>,
    )
}
