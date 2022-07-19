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
) {
    private val subscriberJSONObject = jsonObject.getJSONObject("subscriber")

    val activeSubscriptions: Set<String> by lazy {
        activeIdentifiers(allExpirationDatesByProduct)
    }

    val nonSubscriptionTransactions: List<Transaction> by lazy {
        val nonSubscriptionTransactionList = mutableListOf<Transaction>()
        val nonSubscriptions = subscriberJSONObject.getJSONObject("non_subscriptions")
        nonSubscriptions.keys().forEach { productId ->
            val arrayOfNonSubscriptions = nonSubscriptions.getJSONArray(productId)
            for (i in 0 until arrayOfNonSubscriptions.length()) {
                val transactionJSONObject = arrayOfNonSubscriptions.getJSONObject(i)
                val revenuecatId = transactionJSONObject.getString("id")
                val purchaseDate = transactionJSONObject.getString("purchase_date")

                val transaction = Transaction(productId, revenuecatId, purchaseDate)
                nonSubscriptionTransactionList.add(transaction)
            }
        }
        nonSubscriptionTransactionList.sortedBy { it.purchaseDate }
    }

    val allPurchasedSkus: Set<String> by lazy {
        this.nonSubscriptionTransactions.map { it.productId }.toSet() + allExpirationDatesByProduct.keys
    }

    private fun activeIdentifiers(expirations: Map<String, Date?>): Set<String> {
        return expirations.filterValues { date -> date == null || date.after(requestDate) }.keys
    }
}
