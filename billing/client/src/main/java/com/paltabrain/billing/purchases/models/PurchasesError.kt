package com.paltabrain.billing.purchases.models

data class PurchasesError(
    val code: PurchasesErrorCode,
    val underlyingErrorMessage: String? = null,
)
