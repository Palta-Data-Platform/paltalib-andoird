package com.paltabrain.purchases.extensions

import com.appsflyer.deeplink.DeepLink

private const val APPS_FLYER_OG_DEFERRED_DEEPLINK_VALUE = "deep_link_sub1"

fun DeepLink.getDeferredDeepLink(): String? {
    return getStringValue(APPS_FLYER_OG_DEFERRED_DEEPLINK_VALUE)
}
