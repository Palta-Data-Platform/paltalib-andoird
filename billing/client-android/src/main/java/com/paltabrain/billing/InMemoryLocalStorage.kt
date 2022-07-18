package com.paltabrain.billing

import com.paltabrain.billing.data.ProductDetails
import com.paltabrain.billing.data.Purchase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InMemoryLocalStorage : LocalStorage {

    private val _currentProducts = MutableStateFlow<List<ProductDetails>>(emptyList())
    private val _currentPurchase = MutableStateFlow<List<Purchase>>(emptyList())

    override val currentProducts: StateFlow<List<ProductDetails>>
        get() = _currentProducts.asStateFlow()

    override val currentPurchase: StateFlow<List<Purchase>>
        get() = _currentPurchase.asStateFlow()

    override fun savePurchase(purchases: List<Purchase>) {
        CoroutineScope(Dispatchers.Default).launch {
            _currentPurchase.emit(purchases)
        }
    }

    override fun saveProducts(products: List<ProductDetails>) {
        CoroutineScope(Dispatchers.Default).launch {
            _currentProducts.emit(products)
        }
    }
}