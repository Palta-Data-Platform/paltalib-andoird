package com.paltabrain.billing.mappers

import com.paltabrain.billing.data.BillingResult

interface BillingResultMapper<T> {

    fun map(result: T): BillingResult
}
