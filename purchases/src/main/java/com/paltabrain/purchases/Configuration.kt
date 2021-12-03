package com.paltabrain.purchases

data class Configuration(val revenueCatApiKey: String,
                         val revenueCatUserID: String?,
                         val revenueCatDebugLogsEnabled: Boolean,
                         val webSubscriptionID: String?,
                         val useDefaultAttributionDelegate: Boolean)
