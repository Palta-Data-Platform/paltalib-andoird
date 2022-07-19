package com.paltabrain.billing.rc.mappers

import com.paltabrain.billing.mappers.Mapper
import com.paltabrain.billing.purchases.ProductType
import com.paltabrain.billing.purchases.models.PurchaseState
import com.paltabrain.billing.purchases.models.PurchaseType
import com.paltabrain.billing.purchases.models.StoreTransaction

import com.revenuecat.purchases.models.StoreTransaction as RcStoreTransaction
import com.revenuecat.purchases.ProductType as RcProductType
import com.revenuecat.purchases.models.PurchaseState as RcPurchaseState
import com.revenuecat.purchases.models.PurchaseType as RcPurchaseType

class StoreTransactionMapper : Mapper<StoreTransaction, RcStoreTransaction> {

    override fun mapToEntity(data: StoreTransaction): RcStoreTransaction {
        val type = when (data.type) {
            ProductType.SUBS -> RcProductType.SUBS
            ProductType.INAPP -> RcProductType.INAPP
            ProductType.UNKNOWN -> RcProductType.UNKNOWN
        }
        val purchaseState = when (data.purchaseState) {
            PurchaseState.UNSPECIFIED_STATE -> RcPurchaseState.UNSPECIFIED_STATE
            PurchaseState.PURCHASED -> RcPurchaseState.PURCHASED
            PurchaseState.PENDING -> RcPurchaseState.PENDING
        }
        val purchaseType = when (data.purchaseType) {
            PurchaseType.GOOGLE_PURCHASE -> RcPurchaseType.GOOGLE_PURCHASE
            PurchaseType.GOOGLE_RESTORED_PURCHASE -> RcPurchaseType.GOOGLE_RESTORED_PURCHASE
            PurchaseType.AMAZON_PURCHASE -> RcPurchaseType.AMAZON_PURCHASE
        }

        return RcStoreTransaction(
            orderId = data.orderId,
            skus = data.skus,
            type = type,
            purchaseTime = data.purchaseTime,
            purchaseToken = data.purchaseToken,
            purchaseState = purchaseState,
            isAutoRenewing = data.isAutoRenewing,
            signature = data.signature,
            originalJson = data.originalJson,
            presentedOfferingIdentifier = data.presentedOfferingIdentifier,
            storeUserID = data.storeUserID,
            purchaseType = purchaseType,
            marketplace = data.marketplace,
        )
    }

    override fun mapToData(entity: RcStoreTransaction): StoreTransaction {
        val type = when (entity.type) {
            RcProductType.SUBS -> ProductType.SUBS
            RcProductType.INAPP -> ProductType.INAPP
            RcProductType.UNKNOWN -> ProductType.UNKNOWN
        }
        val purchaseState = when (entity.purchaseState) {
            RcPurchaseState.UNSPECIFIED_STATE -> PurchaseState.UNSPECIFIED_STATE
            RcPurchaseState.PURCHASED -> PurchaseState.PURCHASED
            RcPurchaseState.PENDING -> PurchaseState.PENDING
        }
        val purchaseType = when (entity.purchaseType) {
            RcPurchaseType.GOOGLE_PURCHASE -> PurchaseType.GOOGLE_PURCHASE
            RcPurchaseType.GOOGLE_RESTORED_PURCHASE -> PurchaseType.GOOGLE_RESTORED_PURCHASE
            RcPurchaseType.AMAZON_PURCHASE -> PurchaseType.AMAZON_PURCHASE
        }

        return StoreTransaction(
            orderId = entity.orderId,
            skus = entity.skus,
            type = type,
            purchaseTime = entity.purchaseTime,
            purchaseToken = entity.purchaseToken,
            purchaseState = purchaseState,
            isAutoRenewing = entity.isAutoRenewing,
            signature = entity.signature,
            originalJson = entity.originalJson,
            presentedOfferingIdentifier = entity.presentedOfferingIdentifier,
            storeUserID = entity.storeUserID,
            purchaseType = purchaseType,
            marketplace = entity.marketplace,
        )
    }
}
