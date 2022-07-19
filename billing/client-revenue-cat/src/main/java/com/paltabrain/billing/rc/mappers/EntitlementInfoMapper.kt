package com.paltabrain.billing.rc.mappers

import com.paltabrain.billing.mappers.Mapper
import com.paltabrain.billing.purchases.models.EntitlementInfo
import com.paltabrain.billing.purchases.models.OwnershipType
import com.paltabrain.billing.purchases.models.PeriodType
import com.paltabrain.billing.purchases.models.Store

import com.revenuecat.purchases.OwnershipType as RcOwnershipType
import com.revenuecat.purchases.Store as RcStore
import com.revenuecat.purchases.PeriodType as RcPeriodType
import com.revenuecat.purchases.EntitlementInfo as RcEntitlementInfo

class EntitlementInfoMapper : Mapper<EntitlementInfo, RcEntitlementInfo> {

    override fun mapToEntity(data: EntitlementInfo): RcEntitlementInfo {
        val periodType = data.periodType.toRcPeriodType()
        val store = data.store.toRcStore()
        val ownershipType = data.ownershipType.toRcOwnershipType()
        return RcEntitlementInfo(
            identifier = data.identifier,
            isActive = data.isActive,
            willRenew = data.willRenew,
            periodType = periodType,
            latestPurchaseDate = data.latestPurchaseDate,
            originalPurchaseDate = data.originalPurchaseDate,
            expirationDate = data.expirationDate,
            store = store,
            productIdentifier = data.productIdentifier,
            isSandbox = data.isSandbox,
            unsubscribeDetectedAt = data.unsubscribeDetectedAt,
            billingIssueDetectedAt = data.billingIssueDetectedAt,
            ownershipType = ownershipType,
            jsonObject = data.jsonObject,
        )
    }

    override fun mapToData(entity: RcEntitlementInfo): EntitlementInfo {
        val periodType = entity.periodType.toPeriodType()
        val store = entity.store.toStore()
        val ownershipType = entity.ownershipType.toOwnershipType()
        return EntitlementInfo(
            identifier = entity.identifier,
            isActive = entity.isActive,
            willRenew = entity.willRenew,
            periodType = periodType,
            latestPurchaseDate = entity.latestPurchaseDate,
            originalPurchaseDate = entity.originalPurchaseDate,
            expirationDate = entity.expirationDate,
            store = store,
            productIdentifier = entity.productIdentifier,
            isSandbox = entity.isSandbox,
            unsubscribeDetectedAt = entity.unsubscribeDetectedAt,
            billingIssueDetectedAt = entity.billingIssueDetectedAt,
            ownershipType = ownershipType,
            jsonObject = entity.rawData,
        )
    }

    private fun PeriodType.toRcPeriodType(): RcPeriodType {
        return when (this) {
            PeriodType.NORMAL -> RcPeriodType.NORMAL
            PeriodType.INTRO -> RcPeriodType.INTRO
            PeriodType.TRIAL -> RcPeriodType.TRIAL
        }
    }

    private fun Store.toRcStore(): RcStore {
        return when (this) {
            Store.APP_STORE -> RcStore.APP_STORE
            Store.MAC_APP_STORE -> RcStore.MAC_APP_STORE
            Store.PLAY_STORE -> RcStore.PLAY_STORE
            Store.STRIPE -> RcStore.STRIPE
            Store.PROMOTIONAL -> RcStore.PROMOTIONAL
            Store.UNKNOWN_STORE -> RcStore.UNKNOWN_STORE
            Store.AMAZON -> RcStore.AMAZON
        }
    }

    private fun OwnershipType.toRcOwnershipType(): RcOwnershipType {
        return when (this) {
            OwnershipType.PURCHASED -> RcOwnershipType.PURCHASED
            OwnershipType.FAMILY_SHARED -> RcOwnershipType.FAMILY_SHARED
            OwnershipType.UNKNOWN -> RcOwnershipType.UNKNOWN
        }
    }

    private fun RcPeriodType.toPeriodType(): PeriodType {
        return when (this) {
            RcPeriodType.NORMAL -> PeriodType.NORMAL
            RcPeriodType.INTRO -> PeriodType.INTRO
            RcPeriodType.TRIAL -> PeriodType.TRIAL
        }
    }

    private fun RcStore.toStore(): Store {
        return when (this) {
            RcStore.APP_STORE -> Store.APP_STORE
            RcStore.MAC_APP_STORE -> Store.MAC_APP_STORE
            RcStore.PLAY_STORE -> Store.PLAY_STORE
            RcStore.STRIPE -> Store.STRIPE
            RcStore.PROMOTIONAL -> Store.PROMOTIONAL
            RcStore.UNKNOWN_STORE -> Store.UNKNOWN_STORE
            RcStore.AMAZON -> Store.AMAZON
        }
    }

    private fun RcOwnershipType.toOwnershipType(): OwnershipType {
        return when (this) {
            RcOwnershipType.PURCHASED -> OwnershipType.PURCHASED
            RcOwnershipType.FAMILY_SHARED -> OwnershipType.FAMILY_SHARED
            RcOwnershipType.UNKNOWN -> OwnershipType.UNKNOWN
        }
    }
}
