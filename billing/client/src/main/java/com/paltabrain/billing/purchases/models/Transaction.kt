package com.paltabrain.billing.purchases.models

data class Transaction(
    val revenuecatId: String,
    val productId: String,
    val purchaseDate: String,
)
