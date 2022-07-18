package com.paltabrain.purchases.demo.ui.screens.info

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paltabrain.billing.data.ProductDetails
import com.paltabrain.billing.data.Purchase
import com.paltabrain.billing.purchases.Offering
import com.paltabrain.billing.purchases.Offerings
import com.paltabrain.billing.purchases.ProductType
import com.paltabrain.billing.purchases.models.StoreProduct
import com.paltabrain.purchases.demo.ui.theme.PurchasesTheme
import com.paltabrain.purchases.demo.ui.widgets.OfferingView
import com.paltabrain.purchases.demo.ui.widgets.PackageView
import com.paltabrain.purchases.demo.ui.widgets.ProductView

@Composable
fun Info(
    factory: ViewModelProvider.Factory
) {
    val viewModel = viewModel(
        modelClass = InfoViewModel::class.java,
        factory = factory,
    )
    val offerings by viewModel.offerings.collectAsState()
    val currentActivity = LocalContext.current as Activity

    InfoView(
        offerings = offerings,
        buyClicked = { viewModel.buy(currentActivity, it) },
    )
}

@Composable
private fun InfoView(
    offerings: List<Offering> = emptyList(),
    buyClicked: (storeProduct: StoreProduct) -> Unit = {},
) {
    LazyColumn {
        val inApp = offerings
            .flatMap { it.availablePackages }
            .toList()
            .filter { it.product.type == ProductType.INAPP }

        val subs = offerings
            .flatMap { it.availablePackages }
            .toList()
            .filter { it.product.type == ProductType.SUBS }
        item {
            Text(text = "IN_APP")
        }
        items(inApp) {
            PackageView(
                pack = it,
                buyClick = buyClicked,
            )
        }

        item {
            Text(text = "SUBS")
        }
        items(subs) {
            PackageView(
                pack = it,
                buyClick = buyClicked,
            )
        }
    }
}

@Preview
@Composable
private fun InfoPreview() {
    PurchasesTheme() {
        InfoView()
    }
}