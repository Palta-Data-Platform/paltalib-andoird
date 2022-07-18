package com.paltabrain.purchases.demo.ui.screens.store

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Store() {
}

@Composable
fun StoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Header(Modifier.weight(1f))
        Body(Modifier.weight(1f))
        Footer(Modifier.weight(0.6f))
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Cyan)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Palta Store",
            style = MaterialTheme.typography.displayMedium
        )
        Text(text = "Go to premium")
        Text(text = "Subtitle")
    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        PayButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            price = "39.99 EUR",
            periodPrice = "per year",
            badge = "Best price",
            usesTime = "12 months"
        )
        Spacer(modifier = Modifier.weight(1f))
        PayButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            price = "26.99 EUR",
            periodPrice = "per 3 months",
            badge = "Most Popular",
            usesTime = "3 months"
        )
        Spacer(modifier = Modifier.weight(1f))
        PayButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            price = "9.99 EUR",
            periodPrice = "per 1 month",
            badge = "Most Popular",
            usesTime = "1 months"
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun Footer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Cyan)
    ) {
        Text(text = "Restore")
    }
}

@Composable
fun PayButton(
    modifier: Modifier = Modifier,
    price: String,
    periodPrice: String,
    badge: String,
    usesTime: String
) {
    ElevatedButton(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        onClick = { /*TODO*/ }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = usesTime
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = badge
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = price,
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = periodPrice
                )
            }
        }
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true
)
@Composable
fun PreviewStoreScreen() {
    StoreScreen()
}