package com.paltabrain.billing.rc

import android.content.Context

data class PurchasesConfiguration(
    val applicationContext: Context,
    val apiKey: String,
    val appUserId: String,
    val observerMode: Boolean,
    val debugLogsEnabled: Boolean,
)
