package com.paltabrain.billing.purchases.models

import org.json.JSONObject
import java.util.*

data class CustomerInfo(
    val entitlements: EntitlementInfos,
    @Deprecated(
        "Use nonSubscriptionTransactions instead",
        ReplaceWith("nonSubscriptionTransactions.map{ it.productId }.toSet()")
    )
    val purchasedNonSubscriptionSkus: Set<String>,
    val allExpirationDatesByProduct: Map<String, Date?>,
    val allPurchaseDatesByProduct: Map<String, Date?>,
    val requestDate: Date,
    val jsonObject: JSONObject,
    val schemaVersion: Int,
    val firstSeen: Date,
    val originalAppUserId: String,
    val managementURL: String?,
    val originalPurchaseDate: Date?
)