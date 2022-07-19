package com.paltabrain.billing

import com.paltabrain.billing.data.ProductDetails
import com.paltabrain.billing.data.Purchase
import kotlinx.coroutines.flow.StateFlow

interface LocalStorage {
    val currentProducts: StateFlow<List<ProductDetails>>
    val currentPurchase: StateFlow<List<Purchase>>

    fun savePurchase(purchases: List<Purchase>)
    fun saveProducts(products: List<ProductDetails>)
}
