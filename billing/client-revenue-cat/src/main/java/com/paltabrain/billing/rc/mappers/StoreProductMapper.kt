package com.paltabrain.billing.rc.mappers


import com.paltabrain.billing.mappers.Mapper
import com.paltabrain.billing.purchases.ProductType
import com.paltabrain.billing.purchases.models.StoreProduct

import com.revenuecat.purchases.ProductType as RcProductType
import com.revenuecat.purchases.models.StoreProduct as RcStoreProduct

class StoreProductMapper : Mapper<StoreProduct, RcStoreProduct> {

    override fun mapToEntity(data: StoreProduct): RcStoreProduct {
        val type = when (data.type) {
            ProductType.SUBS -> RcProductType.SUBS
            ProductType.INAPP -> RcProductType.INAPP
            ProductType.UNKNOWN -> RcProductType.UNKNOWN
        }
        return RcStoreProduct(
            sku = data.sku,
            type = type,
            price = data.price,
            priceAmountMicros = data.priceAmountMicros,
            priceCurrencyCode = data.priceCurrencyCode,
            originalPrice = data.originalPrice,
            originalPriceAmountMicros = data.originalPriceAmountMicros,
            title = data.title,
            description = data.description,
            subscriptionPeriod = data.subscriptionPeriod,
            freeTrialPeriod = data.freeTrialPeriod,
            introductoryPrice = data.introductoryPrice,
            introductoryPriceAmountMicros = data.introductoryPriceAmountMicros,
            introductoryPricePeriod = data.introductoryPricePeriod,
            introductoryPriceCycles = data.introductoryPriceCycles,
            iconUrl = data.iconUrl,
            originalJson = data.originalJson,
        )
    }

    override fun mapToData(entity: RcStoreProduct): StoreProduct {
        val type = when (entity.type) {
            RcProductType.SUBS -> ProductType.SUBS
            RcProductType.INAPP -> ProductType.INAPP
            RcProductType.UNKNOWN -> ProductType.UNKNOWN
        }
        return StoreProduct(
            sku = entity.sku,
            type = type,
            price = entity.price,
            priceAmountMicros = entity.priceAmountMicros,
            priceCurrencyCode = entity.priceCurrencyCode,
            originalPrice = entity.originalPrice,
            originalPriceAmountMicros = entity.originalPriceAmountMicros,
            title = entity.title,
            description = entity.description,
            subscriptionPeriod = entity.subscriptionPeriod,
            freeTrialPeriod = entity.freeTrialPeriod,
            introductoryPrice = entity.introductoryPrice,
            introductoryPriceAmountMicros = entity.introductoryPriceAmountMicros,
            introductoryPricePeriod = entity.introductoryPricePeriod,
            introductoryPriceCycles = entity.introductoryPriceCycles,
            iconUrl = entity.iconUrl,
            originalJson = entity.originalJson,
        )
    }
}