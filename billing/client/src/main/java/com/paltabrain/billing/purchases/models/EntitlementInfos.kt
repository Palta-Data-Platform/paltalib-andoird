package com.paltabrain.billing.purchases.models


data class EntitlementInfos(
    val all: Map<String, EntitlementInfo>
) {
    val active = all.filter { it.value.isActive }
}
