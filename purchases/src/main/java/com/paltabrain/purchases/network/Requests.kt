package com.paltabrain.purchases.network

import com.paltabrain.purchases.Constants
import com.paltabrain.purchases.extensions.toBodyRequest
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import org.json.JSONObject

object Requests {

    private val mediaType: MediaType = "application/json".toMediaType()

    fun restoreRequest(email: String, webSubscriptionId: String): Request {
        return Request.Builder().apply {
            val jsonObject = JSONObject()
            jsonObject.put("email", email)
            jsonObject.put("web_subscription_id", webSubscriptionId)

            val body = jsonObject.toString().toBodyRequest(mediaType)
            url(Constants.BASE_URL.plus(Constants.SEND_RESTORE_SUBSCRIPTION_REQUEST))
            post(body)
        }.build()
    }

    fun cancelSubscriptionRequest(
        revenueCatID: String,
        webSubscriptionID: String
    ): Request {
        return Request.Builder().apply {
            val jsonObject = JSONObject()
            jsonObject.put("revenue_cat_id", revenueCatID)
            jsonObject.put("web_subscription_id", webSubscriptionID)

            val body = jsonObject.toString().toBodyRequest(mediaType)
            url(Constants.BASE_URL.plus(Constants.UNSUBSCRIBE_REQUEST))
            post(body)
        }.build()
    }

    fun subscriptionStatusRequest(revenueCatId: String, webSubscriptionId: String): Request {
        return Request.Builder().apply {
            val statusUrl =
                Constants.BASE_URL.plus(Constants.SUBSCRIPTION_STATUS_REQUEST).toHttpUrl()
                    .newBuilder().apply {
                        addQueryParameter(Constants.REVENUE_CAT_ID_PARAMETER, revenueCatId)
                        addQueryParameter(
                            Constants.WEB_SUBSCRIPTION_ID_PARAMETER,
                            webSubscriptionId
                        )
                    }.build()

            url(statusUrl)
        }.build()
    }

    fun sendDeeplinkToEmailRequest(
        provider: String, customerId: String,
        webSubscriptionId: String, email: String
    ): Request {
        return Request.Builder().apply {
            val jsonObject = JSONObject()
            jsonObject.put("provider", provider)
            jsonObject.put("customer_id", customerId)
            jsonObject.put("web_subscription_id", webSubscriptionId)
            jsonObject.put("email", email)

            val body = jsonObject.toString().toBodyRequest(mediaType)
            url(Constants.BASE_URL.plus(Constants.UNSUBSCRIBE_REQUEST))
            post(body)
        }.build()
    }
}
