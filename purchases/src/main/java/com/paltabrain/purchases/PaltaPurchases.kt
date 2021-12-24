package com.paltabrain.purchases

import android.content.Context
import com.appsflyer.deeplink.DeepLinkResult
import com.google.gson.Gson
import com.paltabrain.attribution.PaltaAttribution
import com.paltabrain.core.NetworkClient
import com.paltabrain.core.NetworkResult
import com.paltabrain.entity.TargetList
import com.paltabrain.purchases.attribution.DefaultPurchasesDelegate
import com.paltabrain.purchases.network.Requests
import com.revenuecat.purchases.PurchaserInfo
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.identifyWith
import com.revenuecat.purchases.interfaces.UpdatedPurchaserInfoListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

private typealias ErrorFunction = (error: PurchasesError) -> Unit
private typealias ReceivePurchaserInfoSuccessFunction = (purchaserInfo: PurchaserInfo) -> Unit

private val ON_ERROR_STUB: ErrorFunction = {}
private val ON_SUCCESS_STUB: ReceivePurchaserInfoSuccessFunction = {}

class PaltaPurchases private constructor(
) {

    lateinit var purchases: Purchases
    private val adapter = com.paltabrain.attribution.PaltaAppsflyerAdapter.instance()
    private val paltaAttribution = PaltaAttribution.instance

    companion object {
        val instance = PaltaPurchases()
    }

    private var isConfigured = false
    private var webSubscriptionID: String? = null

    fun configure(
        context: Context,
        revenueCatApiKey: String,
        userID: String?,
        revenueCatDebugLogsEnabled: Boolean = false,
        webSubscriptionID: String?,
        useDefaultAttributionDelegate: Boolean = true
    ) {
        configure(
            context, Configuration(
                revenueCatApiKey = revenueCatApiKey,
                revenueCatUserID = userID,
                revenueCatDebugLogsEnabled = revenueCatDebugLogsEnabled,
                webSubscriptionID = webSubscriptionID,
                useDefaultAttributionDelegate = useDefaultAttributionDelegate
            )
        )
    }

    fun configure(context: Context, configuration: Configuration) {
        isConfigured = true

        Purchases.debugLogsEnabled = configuration.revenueCatDebugLogsEnabled
        Purchases.configure(
            context,
            apiKey = configuration.revenueCatApiKey,
            appUserID = configuration.revenueCatUserID
        )

        webSubscriptionID = configuration.webSubscriptionID
        purchases = Purchases.sharedInstance
        purchases.setAppsflyerID(adapter.getAppsflyerUID(context))
        if (configuration.useDefaultAttributionDelegate) {
            paltaAttribution.setDefaultPaltaAttributionDelegate(DefaultPurchasesDelegate())
        }

        purchases.updatedPurchaserInfoListener = UpdatedPurchaserInfoListener {
            println("purchaseInfo: ${it.activeSubscriptions}")
            println("purchaseInfo: ${it.allPurchasedSkus}")
        }
    }

    fun getSubscriptionStatus(revenueCatId: String, callback: (String?) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            webSubscriptionID?.let {
                val request = Requests.subscriptionStatusRequest(revenueCatId, it)
                NetworkClient().performRequest(request) { response ->
                    callback(handleNetworkResult(response))
                }
            }
        }
    }

    fun sendDeeplinkToEmail(
        provider: String, customerId: String, email: String,
        callback: (String?) -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            webSubscriptionID?.let {
                val request = Requests.sendDeeplinkToEmailRequest(provider, customerId, it, email)
                NetworkClient().performRequest(request) { response ->
                    callback(handleNetworkResult(response))
                }
            }
        }
    }

    fun cancelSubscription(revenueCatID: String, callback: (String?) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            webSubscriptionID?.let {
                val request = Requests.cancelSubscriptionRequest(revenueCatID, it)
                NetworkClient().performRequest(request) { response ->
                    callback(handleNetworkResult(response))
                }
            }
        }
    }

    /**
     * Send restore deeplink to email
     */

    fun sendRestoreLink(email: String, callback: (String?) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            webSubscriptionID?.let {
                val request = Requests.restoreRequest(email, it)
                NetworkClient().performRequest(request) { response ->
                    callback(handleNetworkResult(response))
                }
            }
        }
    }

    private fun handleNetworkResult(result: NetworkResult<String>) : String? {
       return when (result) {
            is NetworkResult.Success -> {
                result.data
            }
            is NetworkResult.Error -> {
                result.data?.let {
                    printErr(result.message.toString())
                    null
                }
            }
        }
    }

    fun setUserId(
        userID: String, onError: ErrorFunction = ON_ERROR_STUB,
        onSuccess: ReceivePurchaserInfoSuccessFunction = ON_SUCCESS_STUB
    ) {
        purchases.identifyWith(userID, onError, onSuccess)
    }

    fun didResolveDeepLink(result: DeepLinkResult) {
        when (result.status) {
            DeepLinkResult.Status.NOT_FOUND -> println("Deep link not found")
            DeepLinkResult.Status.ERROR -> println("Deep link not found")
            DeepLinkResult.Status.FOUND -> {
                result.deepLink?.let {
                    val deepLinkStr: String = it.toString()
                    print("DeepLink data is: $deepLinkStr")
                    if (it.isDeferred == true) {
                        println("This is a deferred deep link")
                    } else {
                        println("This is a direct deep link")
                    }

                    val deepLink = com.paltabrain.attribution.DeepLink(
                        clickEvent = it.clickEvent,
                        deeplinkValue = it.deepLinkValue,
                        matchType = it.matchType,
                        clickHTTPReferrer = it.clickHttpReferrer,
                        mediaSource = it.mediaSource,
                        campaign = it.campaign,
                        campaignId = it.campaignId,
                        afSub1 = it.afSub1,
                        afSub2 = it.afSub2,
                        afSub3 = it.afSub3,
                        afSub4 = it.afSub4,
                        afSub5 = it.afSub5,
                        isDeferred = it.isDeferred == true
                    )
                }
            }

            else -> println("Deep link status is invalid")
        }
    }

    private fun printErr(errorMsg: String) {
        System.err.println(errorMsg)
    }
}
