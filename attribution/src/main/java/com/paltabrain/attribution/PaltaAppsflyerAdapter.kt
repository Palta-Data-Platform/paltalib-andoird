package com.paltabrain.attribution

import android.content.Context
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.deeplink.DeepLinkListener

class PaltaAppsflyerAdapter private constructor(
    private val appsflyerInstance: AppsFlyerLib
) {

    companion object {
        @Volatile
        private var instance: PaltaAppsflyerAdapter? = null

        @Synchronized
        fun instance(): PaltaAppsflyerAdapter =
            instance ?: PaltaAppsflyerAdapter(AppsFlyerLib.getInstance()).also {
                instance = it
            }
    }

    fun getAppsflyerUID(context: Context): String {
        return appsflyerInstance.getAppsFlyerUID(context)
    }

    fun setCustomerUserID(userID: String) {
        appsflyerInstance.setCustomerUserId(userID)
    }

    fun init(
        appsFlyerKey: String, conversionDataListener: AppsFlyerConversionListener,
        context: Context
    ) {
        appsflyerInstance.init(appsFlyerKey, conversionDataListener, context)
    }

    fun start(context: Context) {
        appsflyerInstance.start(context)
    }

    fun setAppInviteOneLink(oneLinkId: String) {
        appsflyerInstance.setAppInviteOneLink(oneLinkId)
    }

    fun subscribeForDeepLink(deepLinkListener: DeepLinkListener) {
        appsflyerInstance.subscribeForDeepLink(deepLinkListener)
    }
}
