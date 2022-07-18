package com.paltabrain.billing.interfaces

import com.paltabrain.billing.purchases.models.CustomerInfo

/**
 * Used to handle async updates from [Purchases]. This interface should be implemented to receive updates
 * when the [CustomerInfo] changes.
 */
interface UpdatedCustomerInfoListener {
    /**
     * Called when a new purchaser info has been received
     * @param customerInfo Updated customer info
     */
    fun onReceived(customerInfo: CustomerInfo)
}
