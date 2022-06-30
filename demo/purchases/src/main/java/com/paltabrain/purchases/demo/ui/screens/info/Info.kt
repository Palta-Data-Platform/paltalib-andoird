package com.paltabrain.purchases.demo.ui.screens.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paltabrain.billing.data.Purchase
import com.paltabrain.purchases.demo.ui.theme.PurchasesTheme

@Composable
fun Info() {
    val viewModel = viewModel(
        modelClass = InfoViewModel::class.java,
    )
    val purchases by viewModel.purchases.collectAsState()
    InfoView(
        purchases = purchases,
        updateClick = viewModel::updateClicked,
        buySomethingsClick = viewModel::buySomethingsClicked,
    )
}

@Composable
private fun InfoView(
    purchases: List<Purchase> = emptyList(),
    updateClick: () -> Unit = {},
    buySomethingsClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Current purchases")
        purchases.forEach {
            Text(text = "Purchase id: ${it.orderId}")
        }
        Button(onClick = buySomethingsClick) {
            Text(text = "Byu Somethings")
        }
        Button(onClick = updateClick) {
            Text(text = "Update")
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