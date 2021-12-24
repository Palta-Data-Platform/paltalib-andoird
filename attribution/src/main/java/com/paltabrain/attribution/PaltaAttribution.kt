package com.paltabrain.attribution

import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.deeplink.DeepLinkResult
import com.paltabrain.attribution.utils.Constants

class PaltaAttribution(private val appsflyerAdapter: PaltaAppsflyerAdapter) {

    private var userId: String? = null

    companion object {
        val instance = PaltaAttribution(PaltaAppsflyerAdapter.instance())
    }

    var delegate: PaltaAttributionDelegate? = null

    fun configure(
        context: Context, appsFlyerKey: String, oneLinkId: String,
        enableLog: Boolean = true
    ) {
        val conversionDataListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                delegate?.onConversionDataSuccess(data)
                data?.let {
                    val userId = it[Constants.USER_ID_KEY] as? String
                    userId?.let { id -> delegate?.onReceiveUserID(id) }
                }
            }

            override fun onConversionDataFail(error: String?) {
                delegate?.onConversionDataFail(error)
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                delegate?.onAppOpenAttribution(data)
                data?.let {
                    val userId = it[Constants.USER_ID_KEY]
                    userId?.let { id -> delegate?.onReceiveUserID(id) }
                }
            }

            override fun onAttributionFailure(error: String?) {
                delegate?.onAttributionFailure(error)
            }
        }

        appsflyerAdapter.init(appsFlyerKey, conversionDataListener, context)
        appsflyerAdapter.setAppInviteOneLink(oneLinkId)
        appsflyerAdapter.start(context)
        appsflyerAdapter.subscribeForDeepLink { result ->
            result.status?.let { deepLinkStatus ->
                when (deepLinkStatus) {
                    DeepLinkResult.Status.FOUND -> {
                        val deepLink = result.deepLink
                        val userId = deepLink.getStringValue(Constants.USER_ID_KEY)
                        delegate?.onReceiveUserID(userId)
                        this.userId = userId
                    }
                    else -> {
                        Log.e(
                            "AppsFlyerLib deepLinkStatus",
                            "Deep link not found or there was an error getting Deep Link data"
                        )
                    }
                }
            }
        }

        AppsFlyerLib.getInstance().setDebugLog(enableLog)
    }

    fun setDefaultPaltaAttributionDelegate(defaultDelegate: PurchaseAttributionDelegate) {
        delegate = DefaultAttributionDelegate(defaultDelegate)
    }

    fun getUserId(): String? {
        return userId
    }

    fun setCustomerID(customerID: String) {
        appsflyerAdapter.setCustomerUserID(customerID)
    }
}
