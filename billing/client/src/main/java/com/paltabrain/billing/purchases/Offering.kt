package com.paltabrain.billing.purchases

data class Offering(
    val identifier: String,
    val serverDescription: String,
    val availablePackages: List<Package>
)
