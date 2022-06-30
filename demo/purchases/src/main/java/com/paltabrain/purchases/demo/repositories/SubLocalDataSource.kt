package com.paltabrain.purchases.demo.repositories

import com.paltabrain.billing.data.Purchase
import kotlinx.coroutines.flow.Flow

interface SubLocalDataSource {

    fun getSubscriptions(): Flow<List<Purchase>>

    suspend fun updateSubscriptions(purchases: List<Purchase>)

    suspend fun deleteLocalUserData()
}