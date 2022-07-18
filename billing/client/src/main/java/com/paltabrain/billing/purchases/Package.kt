package com.paltabrain.billing.purchases

import com.paltabrain.billing.purchases.models.StoreProduct


data class Package(
    val identifier: String,
    val packageType: PackageType,
    val product: StoreProduct,
    val offering: String
)

enum class PackageType(val identifier: String?) {
    /**
     * A package that was defined with a custom identifier.
     */
    UNKNOWN(null),

    /**
     * A package that was defined with a custom identifier.
     */
    CUSTOM(null),

    /**
     * A package configured with the predefined lifetime identifier.
     */
    LIFETIME("\$rc_lifetime"),

    /**
     * A package configured with the predefined annual identifier.
     */
    ANNUAL("\$rc_annual"),

    /**
     * A package configured with the predefined six month identifier.
     */
    SIX_MONTH("\$rc_six_month"),

    /**
     * A package configured with the predefined three month identifier.
     */
    THREE_MONTH("\$rc_three_month"),

    /**
     * A package configured with the predefined two month identifier.
     */
    TWO_MONTH("\$rc_two_month"),

    /**
     * A package configured with the predefined monthly identifier.
     */
    MONTHLY("\$rc_monthly"),

    /**
     * A package configured with the predefined weekly identifier.
     */
    WEEKLY("\$rc_weekly"),
}
