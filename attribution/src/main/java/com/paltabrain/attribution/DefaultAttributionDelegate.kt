package com.paltabrain.attribution

class DefaultAttributionDelegate(private val defaultDelegate: PurchaseAttributionDelegate) :
    PaltaAttributionDelegate {

    override fun onReceiveUserID(userID: String?) {
        defaultDelegate.setUserId(userID)
    }

    override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
    }

    override fun onConversionDataFail(error: String?) {
    }

    override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
    }

    override fun onAttributionFailure(error: String?) {
    }
}
