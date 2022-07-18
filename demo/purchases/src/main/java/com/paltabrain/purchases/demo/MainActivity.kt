package com.paltabrain.purchases.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.paltabrain.billing.LocalStorage
import com.paltabrain.billing.data.ProductDetails
import com.paltabrain.billing.data.Purchase
import com.paltabrain.purchases.demo.ui.screens.info.Info
import com.paltabrain.purchases.demo.ui.screens.info.InfoViewModel
import com.paltabrain.purchases.demo.ui.theme.PurchasesTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val billingClient = (applicationContext as App).client
        val logger = (applicationContext as App).logger
        setContent {
            PurchasesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Info(
                        InfoViewModel.factory(
                            billingClient = billingClient,
                            localStorage = object : LocalStorage{
                                override val currentProducts: StateFlow<List<ProductDetails>>
                                    get() = MutableStateFlow(emptyList())
                                override val currentPurchase: StateFlow<List<Purchase>>
                                    get() = MutableStateFlow(emptyList())

                                override fun savePurchase(purchases: List<Purchase>) {
                                }

                                override fun saveProducts(products: List<ProductDetails>) {
                                }
                            }
                        )
                    )
                }
            }
        }
    }
}

