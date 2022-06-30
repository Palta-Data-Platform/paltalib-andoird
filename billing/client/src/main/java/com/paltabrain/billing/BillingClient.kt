package com.paltabrain.billing

interface BillingClient {

    fun startConnection(callback: BillingClientStateListener)
    fun endConnection()

}