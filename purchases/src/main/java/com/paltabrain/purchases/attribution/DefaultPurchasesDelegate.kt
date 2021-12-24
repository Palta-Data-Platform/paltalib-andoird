package com.paltabrain.purchases.attribution

import com.paltabrain.PaltaAnalytics
import com.paltabrain.attribution.PurchaseAttributionDelegate
import com.paltabrain.purchases.PaltaPurchases

class DefaultPurchasesDelegate : PurchaseAttributionDelegate {
    override fun setUserId(userId: String?) {
        userId?.let {
            PaltaPurchases.instance.setUserId(userId)
            PaltaAnalytics.instance.setUserId(userId, true)
        }
    }
}
