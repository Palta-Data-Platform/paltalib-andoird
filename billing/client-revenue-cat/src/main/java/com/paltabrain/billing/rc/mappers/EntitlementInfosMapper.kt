package com.paltabrain.billing.rc.mappers

import com.paltabrain.billing.mappers.Mapper
import com.paltabrain.billing.purchases.models.EntitlementInfos
import com.revenuecat.purchases.EntitlementInfos as RcEntitlementInfos

class EntitlementInfosMapper : Mapper<EntitlementInfos, RcEntitlementInfos> {

    private val entitlementInfoMapper = EntitlementInfoMapper()

    override fun mapToEntity(data: EntitlementInfos): RcEntitlementInfos {
        val all = data.all.mapValues {
            it.value.let(entitlementInfoMapper::mapToEntity)
        }
        return RcEntitlementInfos(all = all)
    }

    override fun mapToData(entity: RcEntitlementInfos): EntitlementInfos {
        val all = entity.all.mapValues {
            it.value.let(entitlementInfoMapper::mapToData)
        }
        return EntitlementInfos(all = all)
    }
}