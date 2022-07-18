package com.paltabrain.purchases.demo.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paltabrain.billing.purchases.Offering
import com.paltabrain.billing.purchases.Package
import com.paltabrain.billing.purchases.models.StoreProduct


@Composable
fun OfferingView(
    offering: Offering,
    buyClick: (StoreProduct) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        offering.availablePackages.forEach {
            PackageView(
                pack = it,
                buyClick = buyClick,
            )
        }
    }
}

@Composable
fun PackageView(
    pack: Package,
    buyClick: (StoreProduct) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        val endSymbol = pack.product.title.indexOfFirst { it.toString() == "(" }.takeIf { it > 0 }
        val title = endSymbol?.let { pack.product.title.substring(0, it) } ?: pack.product.title
        ProductView(
            title = title,
            description = pack.product.description,
            price = pack.product.price,
            buyClick = { buyClick(pack.product) }
        )
    }
}


@Composable
fun ProductView(
    title: String,
    description: String,
    price: String,
    buyClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Image(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterVertically),
            painter = ColorPainter(Color.Cyan),
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterVertically)
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Button(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            onClick = buyClick
        ) {
            Text(
                text = price,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewProductView() {
    ProductView(
        title = "Product Name",
        description = "Product description",
        price = "1 EUR"
    )
}