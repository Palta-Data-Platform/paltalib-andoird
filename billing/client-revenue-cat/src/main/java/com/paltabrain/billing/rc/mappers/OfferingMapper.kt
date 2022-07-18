package com.paltabrain.billing.rc.mappers

import com.paltabrain.billing.mappers.Mapper
import com.paltabrain.billing.purchases.Offering
import com.revenuecat.purchases.Offering as RcOffering

class OfferingMapper : Mapper<Offering, RcOffering> {

    private val packageMapper = PackageMapper()

    override fun mapToEntity(data: Offering): RcOffering {
        return RcOffering(
            identifier = data.identifier,
            serverDescription = data.serverDescription,
            availablePackages = data.availablePackages.map(packageMapper::mapToEntity)
        )
    }

    override fun mapToData(entity: RcOffering): Offering {
        return Offering(
            identifier = entity.identifier,
            serverDescription = entity.serverDescription,
            availablePackages = entity.availablePackages.map(packageMapper::mapToData)
        )
    }
}