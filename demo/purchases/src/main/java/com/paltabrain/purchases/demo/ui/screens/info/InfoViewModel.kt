package com.paltabrain.purchases.demo.ui.screens.info

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.paltabrain.billing.*
import com.paltabrain.billing.data.ProductDetails
import com.paltabrain.billing.data.Purchase
import com.paltabrain.billing.interfaces.Billing
import com.paltabrain.billing.interfaces.PurchaseCallback
import com.paltabrain.billing.purchases.Offering
import com.paltabrain.billing.purchases.Offerings
import com.paltabrain.billing.purchases.models.CustomerInfo
import com.paltabrain.billing.purchases.models.PurchasesError
import com.paltabrain.billing.purchases.models.StoreProduct
import com.paltabrain.billing.purchases.models.StoreTransaction
import com.paltabrain.billing.rc.RcBillingContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InfoViewModel(
    private val billingClient: Billing,
    private val localStorage: LocalStorage,
) : ViewModel() {

    companion object {
        fun factory(
            billingClient: Billing,
            localStorage: LocalStorage,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return InfoViewModel(
                    billingClient = billingClient,
                    localStorage = localStorage,
                ) as T
            }
        }
    }

    private val _offerings = MutableStateFlow<List<Offering>>(emptyList())
    val offerings: StateFlow<List<Offering>> = _offerings.asStateFlow()

    init {
        updateGooglePlayStore()
    }

    fun buy(activity: Activity, storeProduct: StoreProduct) {
        billingClient.purchaseProduct(
            context = RcBillingContext(activity),
            storeProduct = storeProduct,
            callback = object : PurchaseCallback {
                override fun onCompleted(storeTransaction: StoreTransaction, customerInfo: CustomerInfo) {
                    Log.wtf("!@!", "onCompleted:$storeTransaction:$customerInfo")
                    updateGooglePlayStore()
                }

                override fun onError(error: PurchasesError, userCancelled: Boolean) {
                    Log.wtf("!@!", "onError:$error:$userCancelled")
                }
            }
        )
    }

    private fun updateGooglePlayStore() {
        billingClient.getOfferingsWith(
            onError = {
                Log.wtf("!@!", "error:$it")
            },
            onSuccess = {
                _offerings.tryEmit(it.all.map { it.value })
                Log.wtf("!@!", "success:$it")
                Log.wtf("!@!", "current:${it.current}")
                Log.wtf("!@!", "entities:${it.all.entries}")
            }
        )
    }
}