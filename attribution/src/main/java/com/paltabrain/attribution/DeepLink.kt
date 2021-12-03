package com.paltabrain.attribution

import org.json.JSONObject

data class DeepLink(
    val clickEvent: JSONObject,
    val deeplinkValue: String?,
    val matchType: String?,
    val clickHTTPReferrer: String?,
    val mediaSource: String?,
    val campaign: String?,
    val campaignId: String?,
    val afSub1: String?,
    val afSub2: String?,
    val afSub3: String?,
    val afSub4: String?,
    val afSub5: String?,
    val isDeferred: Boolean
)
