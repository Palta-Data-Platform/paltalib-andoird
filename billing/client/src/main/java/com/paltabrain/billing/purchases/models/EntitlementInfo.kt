package com.paltabrain.billing.purchases.models

import org.json.JSONObject
import java.util.*

data class EntitlementInfo(
    val identifier: String,
    val isActive: Boolean,
    val willRenew: Boolean,
    val periodType: PeriodType,
    val latestPurchaseDate: Date,
    val originalPurchaseDate: Date,
    val expirationDate: Date?,
    val store: Store,
    val productIdentifier: String,
    val isSandbox: Boolean,
    val unsubscribeDetectedAt: Date?,
    val billingIssueDetectedAt: Date?,
    val ownershipType: OwnershipType,
    @Deprecated(
        "Use rawData instead",
        replaceWith = ReplaceWith("rawData")
    ) val jsonObject: JSONObject
)


/**
 * Enum of supported stores
 */
enum class Store {
    /**
     * For entitlements granted via Apple App Store.
     */
    APP_STORE,

    /**
     * For entitlements granted via Apple Mac App Store.
     */
    MAC_APP_STORE,

    /**
     * For entitlements granted via Google Play Store.
     */
    PLAY_STORE,

    /**
     * For entitlements granted via Stripe.
     */
    STRIPE,

    /**
     * For entitlements granted via a promo in RevenueCat.
     */
    PROMOTIONAL,

    /**
     * For entitlements granted via an unknown store.
     */
    UNKNOWN_STORE,

    /**
     * For entitlements granted via Amazon store.
     */
    AMAZON
}


/**
 * Enum of supported period types for an entitlement.
 */
enum class PeriodType {
    /**
     * If the entitlement is not under an introductory or trial period.
     */
    NORMAL,

    /**
     * If the entitlement is under a introductory price period.
     */
    INTRO,

    /**
     * If the entitlement is under a trial period.
     */
    TRIAL,
}

/**
 * Enum of supported ownership types for an entitlement.
 */
enum class OwnershipType {
    /**
     * The purchase was made directly by this user.
     */
    PURCHASED,

    /**
     * The purchase has been shared to this user by a family member.
     */
    FAMILY_SHARED,

    /**
     * The purchase has no or an unknown ownership type.
     */
    UNKNOWN,
}
