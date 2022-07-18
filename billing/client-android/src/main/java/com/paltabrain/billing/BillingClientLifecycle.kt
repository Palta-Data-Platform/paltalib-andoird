package com.paltabrain.billing

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.paltabrain.billing.data.BillingResult
import com.paltabrain.billing.interfaces.Billing
import com.paltabrain.core.logger.Logger

class BillingClientLifecycle(
    private val billingClient: Billing,
    private val logger: Logger,
) : DefaultLifecycleObserver,
    BillingClientStateListener {

    companion object {
        private const val TAG = "BillingClientLifecycle"
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        logger.d(TAG, "onCreate")
//        billingClient.startConnection(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        logger.d(TAG, "onDestroy")
//        billingClient.endConnection()
        super.onDestroy(owner)
    }

    override fun onBillingServiceDisconnected() {
        logger.d(TAG, "onBillingServiceDisconnected")
    }

    override fun onBillingSetupFinished(result: BillingResult) {
        logger.d(TAG, "onBillingSetupFinished:$result")
    }
}