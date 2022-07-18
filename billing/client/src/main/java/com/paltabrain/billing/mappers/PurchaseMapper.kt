package com.paltabrain.billing.mappers

import com.paltabrain.billing.data.Purchase

interface PurchaseMapper<T> {

    fun map(purchase: T): Purchase
}
