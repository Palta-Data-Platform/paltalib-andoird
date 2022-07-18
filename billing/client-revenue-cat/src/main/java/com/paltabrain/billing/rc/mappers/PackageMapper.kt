package com.paltabrain.billing.rc.mappers

import com.paltabrain.billing.mappers.Mapper
import com.paltabrain.billing.purchases.Package
import com.paltabrain.billing.purchases.PackageType
import com.revenuecat.purchases.Package as RcPackage
import com.revenuecat.purchases.PackageType as RcPackageType

class PackageMapper : Mapper<Package, RcPackage> {

    private val storeProductMapper = StoreProductMapper()

    override fun mapToEntity(data: Package): RcPackage {
        val packageType = when (data.packageType) {
            PackageType.UNKNOWN -> RcPackageType.UNKNOWN
            PackageType.CUSTOM -> RcPackageType.CUSTOM
            PackageType.LIFETIME -> RcPackageType.LIFETIME
            PackageType.ANNUAL -> RcPackageType.ANNUAL
            PackageType.SIX_MONTH -> RcPackageType.SIX_MONTH
            PackageType.THREE_MONTH -> RcPackageType.THREE_MONTH
            PackageType.TWO_MONTH -> RcPackageType.TWO_MONTH
            PackageType.MONTHLY -> RcPackageType.MONTHLY
            PackageType.WEEKLY -> RcPackageType.WEEKLY
        }
        return RcPackage(
            identifier = data.identifier,
            packageType = packageType,
            product = storeProductMapper.mapToEntity(data.product),
            offering = data.offering,
        )
    }

    override fun mapToData(entity: RcPackage): Package {
        val packageType = when (entity.packageType) {
            RcPackageType.UNKNOWN -> PackageType.UNKNOWN
            RcPackageType.CUSTOM -> PackageType.CUSTOM
            RcPackageType.LIFETIME -> PackageType.LIFETIME
            RcPackageType.ANNUAL -> PackageType.ANNUAL
            RcPackageType.SIX_MONTH -> PackageType.SIX_MONTH
            RcPackageType.THREE_MONTH -> PackageType.THREE_MONTH
            RcPackageType.TWO_MONTH -> PackageType.TWO_MONTH
            RcPackageType.MONTHLY -> PackageType.MONTHLY
            RcPackageType.WEEKLY -> PackageType.WEEKLY
        }
        return Package(
            identifier = entity.identifier,
            packageType = packageType,
            product = storeProductMapper.mapToData(entity.product),
            offering = entity.offering,
        )
    }
}