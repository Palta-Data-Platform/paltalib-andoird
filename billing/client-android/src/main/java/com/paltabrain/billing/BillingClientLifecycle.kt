package com.paltabrain.billing

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.paltabrain.core.logger.Logger

abstract class BillingClientLifecycle(
    context: Context,
    private val billingClient: BillingClient,
    private val logger: Logger,
) : DefaultLifecycleObserver,
    BillingClientStateListener {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        billingClient.startConnection(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        billingClient.endConnection()
    }
}