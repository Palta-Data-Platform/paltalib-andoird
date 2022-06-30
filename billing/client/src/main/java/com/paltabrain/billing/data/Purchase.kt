package com.paltabrain.billing.data

data class Purchase(
    val purchaseState: Int,
    val quantity: Int,
    val purchaseTime: Long,
    val orderId: String,
    val originalJson: String,
    val purchaseToken: String,
    val signature: String,
)
