package com.paltabrain.billing.rc.mappers

import android.net.Uri
import com.paltabrain.billing.mappers.Mapper
import com.paltabrain.billing.purchases.models.CustomerInfo

import com.revenuecat.purchases.CustomerInfo as RcCustomerInfo

class CustomerInfoMapper : Mapper<CustomerInfo, RcCustomerInfo> {

    private val entitlementInfoMapper = EntitlementInfosMapper()

    override fun mapToEntity(data: CustomerInfo): RcCustomerInfo {
        return RcCustomerInfo(
            entitlements = data.entitlements.let { entitlementInfoMapper.mapToEntity(it) },
            purchasedNonSubscriptionSkus = data.purchasedNonSubscriptionSkus,
            allExpirationDatesByProduct = data.allExpirationDatesByProduct,
            allPurchaseDatesByProduct = data.allPurchaseDatesByProduct,
            requestDate = data.requestDate,
            jsonObject = data.jsonObject,
            schemaVersion = data.schemaVersion,
            firstSeen = data.firstSeen,
            originalAppUserId = data.originalAppUserId,
            managementURL = Uri.parse(data.managementURL),
            originalPurchaseDate = data.originalPurchaseDate
        )
    }

    override fun mapToData(entity: RcCustomerInfo): CustomerInfo {
        return CustomerInfo(
            entitlements = entity.entitlements.let { entitlementInfoMapper.mapToData(it) },
            purchasedNonSubscriptionSkus = entity.purchasedNonSubscriptionSkus,
            allExpirationDatesByProduct = entity.allExpirationDatesByProduct,
            allPurchaseDatesByProduct = entity.allPurchaseDatesByProduct,
            requestDate = entity.requestDate,
            jsonObject = entity.rawData,
            schemaVersion = entity.schemaVersion,
            firstSeen = entity.firstSeen,
            originalAppUserId = entity.originalAppUserId,
            managementURL = entity.managementURL.toString(),
            originalPurchaseDate = entity.originalPurchaseDate
        )
    }
}